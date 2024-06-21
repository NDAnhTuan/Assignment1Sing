package com.assignment.InternshipProject.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;
//@Component
public class LoggingFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        long startTime = System.currentTimeMillis();
        String requestId = UUID.randomUUID().toString();

        // Set requestId as a request attribute for later retrieval
        httpRequest.setAttribute("requestId", requestId);

        try {
            // Proceed with the filter chain
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.error("Exception encountered during request processing", e);
            throw e;
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            logRequest(httpRequest, httpResponse, duration, requestId);
        }
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }

    private void logRequest(HttpServletRequest request, HttpServletResponse response, long duration, String requestId) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("Request ID: ").append(requestId).append(", ");
        logMessage.append("Method: ").append(request.getMethod()).append(", ");
        logMessage.append("Path: ").append(request.getRequestURI()).append(", ");
        logMessage.append("Status: ").append(response.getStatus()).append(", ");
        logMessage.append("Duration: ").append(duration).append(" ms");
        log.info(logMessage.toString());
    }
}
