var socket = null;
var pingTimer = null;
var reconnectTimer = null;
var currentUsername = '';
var manualDisconnect = false;
var connecting = false;
var STORE_USER = 'websocket-basic.username';
var STORE_URL = 'websocket-basic.endpoint';
var STORE_AUTO = 'websocket-basic.autoConnect';
var STORE_SEND = 'websocket-basic.sendBroadcast';
var STORE_RECV = 'websocket-basic.recvBroadcast';
var SEND_ON = '__send:on';
var SEND_OFF = '__send:off';
var RECV_ON = '__receive:on';
var RECV_OFF = '__receive:off';
var ONLINE_PREFIX = '__online:';
var PM_PREFIX = '__pm:';
var STORE_THEME = 'websocket-basic.theme';
var STORE_CHATS = 'websocket-basic.chats';
var STORE_TARGET = 'websocket-basic.selectedTarget';
var MAX_CHAT_MESSAGES = 500;
var lastOnlineUsers = '';
var selectedTarget = '';
var chats = {};

jQuery(function ($) {

    function ts() {
        return new Date().toLocaleTimeString();
    }

    function writePing(message) {
        markHeartbeat(true);
        console.log('[' + ts() + '] heartbeat ' + message);
    }

    function writeStatus(message) {
        console.log('[' + ts() + '] ' + message);
    }

    var AVATAR_COLORS = ['#6366f1', '#ec4899', '#f59e0b', '#10b981', '#06b6d4', '#8b5cf6', '#ef4444', '#84cc16'];

    function colorFor(name) {
        var sum = 0;
        var i;
        for (i = 0; i < name.length; i++) {
            sum += name.charCodeAt(i);
        }
        return AVATAR_COLORS[sum % AVATAR_COLORS.length];
    }

    function markHeartbeat(ok) {
        var el = $('#pingLive');
        el.toggleClass('on', !!ok).toggleClass('off', !ok).toggleClass('warn', false);
        $('#pingLabel').text(ok ? ('OK · ' + ts()) : 'Idle');
    }

    function markConnection(on) {
        var el = $('#connLive');
        el.toggleClass('on', !!on).toggleClass('off', !on);
        $('#connLabel').text(on ? ('Connected as ' + currentUsername) : 'Disconnected');
    }

    function chatKey(name) {
        return name || '';
    }

    function getChat(key) {
        if (!chats[key]) {
            chats[key] = [];
        }
        return chats[key];
    }

    function saveChats() {
        try {
            localStorage.setItem(STORE_CHATS, JSON.stringify(chats));
        } catch (e) {
            console.warn('Failed to save chats', e);
        }
    }

    function loadChats() {
        try {
            var raw = localStorage.getItem(STORE_CHATS);
            if (!raw) {
                return;
            }
            var parsed = JSON.parse(raw);
            if (parsed && typeof parsed === 'object') {
                chats = parsed;
            }
        } catch (e) {
            chats = {};
        }
    }

    function saveSelectedTarget() {
        sessionStorage.setItem(STORE_TARGET, selectedTarget || '');
    }

    function loadSelectedTarget() {
        var saved = sessionStorage.getItem(STORE_TARGET);
        selectedTarget = saved || '';
    }

    function parseChatMessage(raw) {
        var text = String(raw || '');
        if (text.indexOf('ERROR:') === 0) {
            return { kind: 'system', from: '', to: '', body: text, whisper: false };
        }
        var pm = text.match(/^\[PM (.+?) → (.+?)\] (.+?): ([\s\S]*)$/);
        if (pm) {
            return { kind: 'chat', from: pm[3], to: pm[2], body: pm[4], whisper: true };
        }
        var chat = text.match(/^([^:]+): ([\s\S]*)$/);
        if (chat) {
            return { kind: 'chat', from: chat[1], to: '', body: chat[2], whisper: false };
        }
        return { kind: 'system', from: '', to: '', body: text, whisper: false };
    }

    function resolveChatKey(parsed, me) {
        if (parsed.kind === 'system') {
            return chatKey(selectedTarget);
        }
        if (parsed.whisper) {
            if (parsed.from === me) {
                return chatKey(parsed.to);
            }
            return chatKey(parsed.from);
        }
        return '';
    }

    function buildMessageNode(parsed, time) {
        var me = currentUsername || $('#username').val().trim();
        var wrap = document.createElement('div');

        if (parsed.kind === 'system') {
            wrap.className = 'msg system';
        } else if (parsed.from && me && parsed.from === me) {
            wrap.className = 'msg mine';
        } else {
            wrap.className = 'msg other';
        }

        var meta = document.createElement('div');
        meta.className = 'meta';
        if (parsed.kind === 'system') {
            meta.textContent = time;
        } else {
            meta.textContent = parsed.from + ' · ' + time;
        }

        var bubble = document.createElement('div');
        bubble.className = 'bubble';
        bubble.appendChild(document.createTextNode(parsed.body));

        wrap.appendChild(meta);
        wrap.appendChild(bubble);
        return wrap;
    }

    function renderActiveChat() {
        var el = document.getElementById('messageOutput');
        el.innerHTML = '';
        var key = chatKey(selectedTarget);
        var list = getChat(key);
        var i;
        for (i = 0; i < list.length; i++) {
            el.appendChild(buildMessageNode(list[i].parsed, list[i].time));
        }
        el.scrollTop = el.scrollHeight;
    }

    function writeMessage(message) {
        var parsed = parseChatMessage(message);
        var me = currentUsername || $('#username').val().trim();
        var key = resolveChatKey(parsed, me);
        var time = ts();
        var list = getChat(key);
        list.push({ parsed: parsed, time: time });
        if (list.length > MAX_CHAT_MESSAGES) {
            chats[key] = list.slice(-MAX_CHAT_MESSAGES);
        }
        saveChats();
        if (key === chatKey(selectedTarget)) {
            var el = document.getElementById('messageOutput');
            el.appendChild(buildMessageNode(parsed, time));
            el.scrollTop = el.scrollHeight;
        }
    }

    function hasUsername() {
        return !!(currentUsername || $('#username').val().trim());
    }

    function saveClientSession(autoConnect) {
        var name = ($('#username').val().trim() || currentUsername);
        if (name) {
            sessionStorage.setItem(STORE_USER, name);
        }
        sessionStorage.setItem(STORE_URL, $('#target').val());
        sessionStorage.setItem(STORE_AUTO, autoConnect ? '1' : '0');
        sessionStorage.setItem(STORE_SEND, $('#sendBroadcast').prop('checked') ? '1' : '0');
        sessionStorage.setItem(STORE_RECV, $('#recvBroadcast').prop('checked') ? '1' : '0');
    }

    function loadFlag(key, checkboxId) {
        var saved = sessionStorage.getItem(key);
        if (saved === '0') {
            $(checkboxId).prop('checked', false);
        } else if (saved === '1') {
            $(checkboxId).prop('checked', true);
        }
    }

    function loadClientSession() {
        var user = sessionStorage.getItem(STORE_USER);
        var url = sessionStorage.getItem(STORE_URL);
        if (user) {
            $('#username').val(user);
        }
        if (url) {
            $('#target').val(url);
        }
        loadFlag(STORE_SEND, '#sendBroadcast');
        loadFlag(STORE_RECV, '#recvBroadcast');
        updateModeTags();
        return sessionStorage.getItem(STORE_AUTO) === '1';
    }

    function updateModeTags() {
        var sendOn = $('#sendBroadcast').prop('checked');
        var recvOn = $('#recvBroadcast').prop('checked');
        $('#sendTag').text(sendOn ? 'send' : 'send off')
            .toggleClass('violet', sendOn)
            .toggleClass('green', !sendOn);
        $('#recvTag').text(recvOn ? 'recv' : 'recv off')
            .toggleClass('violet', recvOn)
            .toggleClass('green', !recvOn);
    }

    function syncSendMode() {
        if (socket && socket.readyState === WebSocket.OPEN) {
            socket.send($('#sendBroadcast').prop('checked') ? SEND_ON : SEND_OFF);
        }
        saveClientSession(sessionStorage.getItem(STORE_AUTO) === '1');
        updateModeTags();
    }

    function syncRecvMode() {
        if (socket && socket.readyState === WebSocket.OPEN) {
            socket.send($('#recvBroadcast').prop('checked') ? RECV_ON : RECV_OFF);
        }
        saveClientSession(sessionStorage.getItem(STORE_AUTO) === '1');
        updateModeTags();
    }

    function syncBothModes() {
        syncSendMode();
        syncRecvMode();
    }

    function appendOnlineItem(list, opts) {
        var li = document.createElement('li');
        var classes = 'online-item';
        if (opts.channel) {
            classes += ' channel';
        }
        if (opts.me) {
            classes += ' me';
        }
        if (opts.active) {
            classes += ' active';
        }
        li.className = classes;
        li.setAttribute('data-name', opts.name);

        var avatar = document.createElement('span');
        avatar.className = 'online-avatar';
        if (opts.channel) {
            avatar.textContent = 'E';
        } else {
            avatar.style.background = colorFor(opts.name);
            avatar.textContent = opts.name.charAt(0).toUpperCase();
        }

        var nameEl = document.createElement('span');
        nameEl.className = 'online-name';
        nameEl.appendChild(document.createTextNode(opts.label || opts.name));
        if (opts.sub) {
            var tip = document.createElement('small');
            tip.textContent = opts.sub;
            nameEl.appendChild(tip);
        }

        li.appendChild(avatar);
        li.appendChild(nameEl);
        list.appendChild(li);
    }

    function rebuildOnlineList(usersCsv) {
        var list = document.getElementById('onlineList');
        var me = ($('#username').val().trim() || currentUsername);
        var names = [];
        var seen = {};
        var i;
        if (usersCsv) {
            names = usersCsv.split(',');
        }
        if (selectedTarget && names.length) {
            var stillOnline = false;
            for (i = 0; i < names.length; i++) {
                if (names[i] === selectedTarget) {
                    stillOnline = true;
                    break;
                }
            }
            if (!stillOnline) {
                selectedTarget = '';
                saveSelectedTarget();
                renderActiveChat();
            }
        }
        list.innerHTML = '';
        if (me && seen[me] !== true) {
            for (i = 0; i < names.length; i++) {
                if (names[i] === me) {
                    seen[me] = true;
                    appendOnlineItem(list, {
                        name: me,
                        me: true,
                        sub: '本人',
                        active: me === selectedTarget
                    });
                    break;
                }
            }
        }
        appendOnlineItem(list, {
            name: '',
            label: 'Everyone',
            sub: 'world channel',
            channel: true,
            active: !selectedTarget
        });
        for (i = 0; i < names.length; i++) {
            var n = names[i];
            if (!n || seen[n]) {
                continue;
            }
            seen[n] = true;
            appendOnlineItem(list, {
                name: n,
                me: false,
                active: n === selectedTarget
            });
        }
    }

    function setOnline(count, users) {
        var n = count == null ? 0 : count;
        lastOnlineUsers = count == null ? '' : (users || '');
        $('#onlineCountTag').text(n);
        $('#onlineLive').text(count == null ? '-' : (n + (lastOnlineUsers ? ' · ' + lastOnlineUsers : '')));
        rebuildOnlineList(lastOnlineUsers);
    }

    function setConnected(on) {
        $('#connect').prop('disabled', on);
        $('#disconnect').prop('disabled', !on);
        $('#send').prop('disabled', !on || !hasUsername());
        $('#message').prop('disabled', !on || !hasUsername());
        $('#username').prop('disabled', on);
        markConnection(on);
        if (!on) {
            setOnline(null, '');
            markHeartbeat(false);
        }
    }

    function startPing() {
        pingTimer = setInterval(function () {
            if (socket) {
                socket.send('ping');
            }
        }, 3000);
    }

    function stopPing() {
        clearInterval(pingTimer);
        pingTimer = null;
    }

    function scheduleReconnect() {
        if (manualDisconnect || socket || sessionStorage.getItem(STORE_AUTO) !== '1') {
            return;
        }
        clearTimeout(reconnectTimer);
        reconnectTimer = setTimeout(function () {
            writeStatus('Connection lost, reconnecting …');
            $('#connect').click();
        }, 1000);
    }

    function connect() {
        if (socket || connecting) {
            return;
        }
        var username = $('#username').val().trim();
        if (!username) {
            writeStatus('Please set a username before connecting.');
            $('#username').focus();
            return;
        }
        connecting = true;
        currentUsername = username;
        var url = $('#target').val();
        var sep = url.indexOf('?') >= 0 ? '&' : '?';
        url = url + sep + 'username=' + encodeURIComponent(username);
        writeStatus('Connecting as "' + username + '" to ' + url + ' …');
        socket = new WebSocket(url);

        socket.onopen = function () {
            connecting = false;
            manualDisconnect = false;
            saveClientSession(true);
            writeStatus('CONNECTED as ' + currentUsername);
            setConnected(true);
            syncBothModes();
            startPing();
        };

        socket.onclose = function () {
            connecting = false;
            writeStatus('DISCONNECTED');
            stopPing();
            socket = null;
            setConnected(false);
            currentUsername = '';
            if (!manualDisconnect) {
                scheduleReconnect();
            }
        };

        socket.onmessage = function (evt) {
            if (evt.data === 'ping') {
                writePing(evt.data);
            } else if (evt.data === SEND_ON || evt.data === SEND_OFF) {
                writeStatus(evt.data === SEND_ON ? 'Send broadcasts: on' : 'Send broadcasts: off');
            } else if (evt.data === RECV_ON || evt.data === RECV_OFF) {
                writeStatus(evt.data === RECV_ON ? 'Receive broadcasts: on' : 'Receive broadcasts: off');
            } else if (evt.data.indexOf(ONLINE_PREFIX) === 0) {
                var rest = evt.data.substring(ONLINE_PREFIX.length);
                var pipe = rest.indexOf('|');
                var count = pipe >= 0 ? rest.substring(0, pipe) : rest;
                var users = pipe >= 0 ? rest.substring(pipe + 1) : '';
                setOnline(count, users);
                writeStatus('Online: ' + count + (users ? ' (' + users + ')' : ''));
            } else {
                writeMessage(evt.data);
            }
        };

        socket.onerror = function () {
            writeStatus('ERROR: connection failed');
        };
    }

    $('#connect').click(connect);

    $('#disconnect').click(function () {
        manualDisconnect = true;
        saveClientSession(false);
        clearTimeout(reconnectTimer);
        if (socket) {
            stopPing();
            socket.close();
        } else {
            writeStatus('Not connected.');
        }
    });

    function send() {
        if (!hasUsername()) {
            writeStatus('Please set a username before sending messages.');
            $('#username').focus();
            return;
        }
        if (socket && socket.readyState === WebSocket.OPEN) {
            var text = $('#message').val();
            if (!text.trim()) {
                return;
            }
            if (selectedTarget) {
                socket.send(PM_PREFIX + selectedTarget + '|' + text);
            } else {
                socket.send(text);
            }
            $('#message').val('').focus();
        } else {
            writeStatus('Not connected.');
        }
    }

    $('#send').click(send);

    $('#onlineList').on('click', '.online-item', function () {
        if ($('#message').prop('disabled')) {
            return;
        }
        selectedTarget = $(this).attr('data-name') || '';
        saveSelectedTarget();
        $('.online-item').removeClass('active');
        $(this).addClass('active');
        renderActiveChat();
        $('#message').focus();
    });

    $('#message').on('keydown', function (e) {
        if (e.key === 'Enter') {
            send();
        }
    });

    $('#username').on('keydown', function (e) {
        if (e.key === 'Enter') {
            connect();
        }
    });

    function currentTheme() {
        return document.documentElement.getAttribute('data-theme') === 'light' ? 'light' : 'dark';
    }

    function applyTheme(theme) {
        document.documentElement.setAttribute('data-theme', theme);
        localStorage.setItem(STORE_THEME, theme);
        $('#themeToggle').text(theme === 'light' ? 'Dark' : 'Light');
    }

    $('#themeToggle').on('click', function () {
        applyTheme(currentTheme() === 'light' ? 'dark' : 'light');
    });

    $('#sendBroadcast').on('change', syncSendMode);
    $('#recvBroadcast').on('change', syncRecvMode);

    applyTheme(localStorage.getItem(STORE_THEME) || currentTheme());
    loadChats();
    loadSelectedTarget();
    setConnected(false);
    updateModeTags();
    loadClientSession();
    renderActiveChat();

    function tryAutoConnect(reason) {
        if (document.visibilityState && document.visibilityState !== 'visible') {
            return;
        }
        if (socket || connecting || manualDisconnect) {
            return;
        }
        if (sessionStorage.getItem(STORE_AUTO) === '0') {
            return;
        }
        $.getJSON('/session').done(function (data) {
            if (socket || connecting || manualDisconnect) {
                return;
            }
            if (sessionStorage.getItem(STORE_AUTO) === '0') {
                return;
            }
            var httpUser = data && data.username ? data.username : '';
            if (httpUser && !$('#username').val().trim()) {
                $('#username').val(httpUser);
            }
            var name = $('#username').val().trim();
            if (!name) {
                return;
            }
            var auto = sessionStorage.getItem(STORE_AUTO);
            if (auto === '1' || httpUser) {
                writeStatus(reason || ('Same session, auto-connecting as "' + name + '" …'));
                connect();
            }
        });
    }

    tryAutoConnect('Restoring session …');
    $(document).on('visibilitychange', function () {
        if (document.visibilityState === 'visible') {
            tryAutoConnect('Tab became visible, auto-connecting …');
        }
    });
    $(window).on('focus', function () {
        tryAutoConnect('Tab focused, auto-connecting …');
    });
});
