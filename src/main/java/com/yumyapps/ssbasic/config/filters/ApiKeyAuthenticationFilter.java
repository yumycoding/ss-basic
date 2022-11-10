package com.yumyapps.ssbasic.config.filters;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@AllArgsConstructor
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {

    private final String keyToAuthenticate;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        log.info("Api Key {}",keyToAuthenticate);

        filterChain.doFilter(request, response); //only when authentication works.
    }
}
