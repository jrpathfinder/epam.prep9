package com.epam.learn.springdata.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Component
public class ExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception exception) {
        ModelAndView model = new ModelAndView();
        model.setView(new MappingJackson2JsonView());
        model.addObject("Exception", exception.getMessage());
        return model;
    }
}
