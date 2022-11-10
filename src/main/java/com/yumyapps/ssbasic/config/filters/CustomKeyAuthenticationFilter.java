package com.yumyapps.ssbasic.config.filters;

import com.yumyapps.ssbasic.config.authentication.CustomAuthentication;
import com.yumyapps.ssbasic.config.manager.CustomAuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomKeyAuthenticationFilter extends OncePerRequestFilter {

    private final CustomAuthenticationManager customAuthenticationManager;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String key = String.valueOf(request.getHeader("key"));

        // 1. create an authentication object which is not yet authenticated
        CustomAuthentication ca = new CustomAuthentication(false, key);

        // 2. delegate the authentication object to the manager
        var a = customAuthenticationManager.authenticate(ca);

        // 3. get back the authentication from the manager

        // 4. if the object is authenticated then send request to the next filter in the chain
        if (a.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(a);
            filterChain.doFilter(request, response); // only when authentication worked
        }

        filterChain.doFilter(request, response); //only when authentication works.
    }
}
