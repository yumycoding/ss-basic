package com.yumyapps.ssbasic.config.provider;

import com.yumyapps.ssbasic.config.authentication.ApiKeyAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Objects;

@RequiredArgsConstructor
public class ApiKeyProvider implements AuthenticationProvider {

    private final String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        ApiKeyAuthentication auth = (ApiKeyAuthentication) authentication;

        auth.setAuthenticated(Objects.nonNull(auth.getKey()) && key.equals(auth.getKey()));
        return auth;
//        throw new BadCredentialsException("Invalid Key");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthentication.class.equals(authentication);
    }
}
