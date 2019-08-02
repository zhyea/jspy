package org.chobit.jspy.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionInterceptor implements HandlerInterceptor {


    private final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    private static final String HOME_URL = "/";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (null != session.getAttribute("appCode")) {
            return true;
        }
        String uri = request.getRequestURI();
        logger.info("Cannot find target value from session, src path:{}", uri);
        if (uri.startsWith("/api")) {
            response.setStatus(401);
            return false;
        }
        request.getRequestDispatcher(HOME_URL).forward(request, response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
