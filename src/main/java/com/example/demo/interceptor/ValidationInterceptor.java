package com.example.demo.interceptor;

import com.example.demo.entity.Contact;
import com.example.demo.enumdef.HttpStatus;
import com.example.demo.exception.InvalidRequestDataException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.runtime.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ValidationInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
//            String body = request.getAttribute("cachedRequestBody") != null ? request.getAttribute("cachedRequestBody").toString() : request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//            request.setAttribute("cachedRequestBody", body);
//            ObjectMapper mapper = new ObjectMapper();
//            Contact contact = mapper.readValue(body, Contact.class);
//            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//            Validator validator = factory.getValidator();
//            Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
//            if (!violations.isEmpty()) {
//                String errorMessage = violations.stream()
//                        .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
//                        .collect(Collectors.joining(", "));
//                LOGGER.error(errorMessage);
//
////            response.setStatus(HttpStatus.BAD_REQUEST.value());
////            response.getWriter().write(violations.iterator().next().getMessage());
//
//                return false;
//            }
//        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

}
