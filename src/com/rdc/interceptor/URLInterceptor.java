package com.rdc.interceptor;

import com.data.Globals;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by drodrigues on 4/20/16.
 */
public class URLInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o,
                           ModelAndView modelAndView) throws Exception {
        Globals globals = (Globals) httpServletRequest.getSession().getAttribute("globals");

        if(globals != null){

            String url = httpServletRequest.getRequestURL().toString();
            String status = globals.getStatus();

            if(! url.contains(status)){ // Status changed
                if(url.contains("/create")) globals.setStatus("create");
                else if(url.contains("/read")) globals.setStatus("read");
                else System.out.println("We're not on the create or read side");
                httpServletRequest.getSession().setAttribute("globals", globals);
                System.out.println("Updated Status to : " + globals.getStatus());
            }

        }
        else{
            System.out.println("Errror, no globals found");
        }


    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
