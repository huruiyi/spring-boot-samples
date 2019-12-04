package com.example.rsession.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class HelloController {
    private static final int EXPIRY = 60 * 30;

    @RequestMapping(value = "/getSessionId")
    @ResponseBody
    public String getSessionId(HttpServletRequest request, HttpServletResponse response) {
        processCookie(request, response);
        Object obj = request.getSession().getAttribute("springboot");
        if (obj == null) {
            obj = "springboot-session-redis session共享" + request.getLocalPort() + "生成";
            HttpSession session = request.getSession();
            session.setAttribute("springboot", obj);
            session.setMaxInactiveInterval(EXPIRY);
        }
        return "端口=" + request.getLocalPort() + " sessionId=" + request.getSession().getId() + "<br/>" + obj;
    }

    public void processCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().toUpperCase().equals("SESSION")) {
                String value = cookie.getValue();

                Cookie resCookie = new Cookie(cookie.getName(), value);
                resCookie.setMaxAge(EXPIRY);// 设置cookie的生命周期为30min
                response.addCookie(resCookie);
            }
        }
    }
}
