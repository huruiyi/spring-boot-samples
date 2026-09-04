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

jQuery(function ($) {

    function ts() {
        return new Date().toLocaleTimeString();
    }

    function appendLine(id, text) {
        var el = document.getElementById(id);
        el.appendChild(document.createTextNode(text));
        el.scrollTop = el.scrollHeight;
    }

    function writePing(message) {
        appendLine('pingOutput', '[' + ts() + '] ' + message + '\n');
    }

    function writeStatus(message) {
        appendLine('statusOutput', '[' + ts() + '] ' + message + '\n');
    }

    function writeMessage(message) {
        appendLine('messageOutput', '[' + ts() + '] ' + message + '\n');
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

    function rebuildSendTo(usersCsv) {
        var sel = document.getElementById('sendTo');
        var keep = sel.value;
        var me = ($('#username').val().trim() || currentUsername);
        var names = [];
        if (usersCsv) {
            names = usersCsv.split(',');
        }
        sel.innerHTML = '';
        sel.appendChild(new Option('Everyone', ''));
        var i;
        var seen = {};
        for (i = 0; i < names.length; i++) {
            var n = names[i];
            if (!n || n === me || seen[n]) {
                continue;
            }
            seen[n] = true;
            sel.appendChild(new Option(n, n));
        }
        var stillThere = false;
        for (i = 0; i < sel.options.length; i++) {
            if (sel.options[i].value === keep) {
                stillThere = true;
                break;
            }
        }
        sel.value = stillThere ? keep : '';
    }

    function setOnline(count, users) {
        $('#onlineCount').text(count == null ? '-' : count);
        $('#onlinePill').attr('title', users || '');
        rebuildSendTo(count == null ? '' : users);
    }

    function setConnected(on) {
        $('#connect').prop('disabled', on);
        $('#disconnect').prop('disabled', !on);
        $('#send').prop('disabled', !on || !hasUsername());
        $('#message').prop('disabled', !on || !hasUsername());
        $('#sendTo').prop('disabled', !on || !hasUsername());
        $('#username').prop('disabled', on);
        $('#connPill').toggleClass('on', on);
        $('#connLabel').text(on ? ('Connected as ' + currentUsername) : 'Disconnected');
        if (!on) {
            setOnline(null, '');
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
            var to = $('#sendTo').val();
            if (to) {
                socket.send(PM_PREFIX + to + '|' + text);
            } else {
                socket.send(text);
            }
            $('#message').val('').focus();
        } else {
            writeStatus('Not connected.');
        }
    }

    $('#send').click(send);

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

    $('#sendBroadcast').on('change', syncSendMode);
    $('#recvBroadcast').on('change', syncRecvMode);

    setConnected(false);
    updateModeTags();
    loadClientSession();

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
