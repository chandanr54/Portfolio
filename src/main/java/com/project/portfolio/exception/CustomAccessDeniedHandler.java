package com.project.portfolio.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {


    private static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        logger.debug("Access Denied: {}", accessDeniedException.getMessage());

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> error = new HashMap<>();
        error.put("status", 403);
        error.put("error", "Forbidden");
        error.put("message", "You are not authorized to access this resource");

        response.getWriter().write(mapper.writeValueAsString(error));

        response.getWriter().write(mapper.writeValueAsString(error));



    }
}
