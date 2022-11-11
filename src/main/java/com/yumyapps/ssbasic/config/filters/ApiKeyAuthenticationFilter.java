package com.yumyapps.ssbasic.config.filters;

import com.yumyapps.ssbasic.config.authentication.ApiKeyAuthentication;
import com.yumyapps.ssbasic.config.manager.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


@Slf4j
@AllArgsConstructor
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {

    private final String keyToAuthenticate;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        log.info("Api Key {}", keyToAuthenticate);

        CustomAuthenticationManager manager = new CustomAuthenticationManager(keyToAuthenticate);

        var requestKey = request.getHeader("x-api-key");

        if ("null".equals(requestKey) || Objects.isNull(requestKey)) {
            filterChain.doFilter(request, response);
        }

        var auth = new ApiKeyAuthentication(requestKey);


        try {
            Authentication a = manager.authenticate(auth);
            if (a.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(a);
                filterChain.doFilter(request, response);

            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            }

        } catch (AuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }


        filterChain.doFilter(request, response); //only when authentication works.
    }
}
