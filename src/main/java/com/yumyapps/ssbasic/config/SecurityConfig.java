package com.yumyapps.ssbasic.config;

import com.yumyapps.ssbasic.config.filters.CustomKeyAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomKeyAuthenticationFilter keyAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .addFilterAt(keyAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .build();

    }


    @Bean
    BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}


//    @Bean
//    public UserDetailsService userDetailsService() {
//        var uds = new InMemoryUserDetailsManager();
//
//        var u1 = User.builder()
//                .username("g1")
//                .password(getPasswordEncoder().encode("12345"))
//                .authorities("read")
//                .build();
//        var u2 = User.builder()
//                .username("admin")
//                .password(getPasswordEncoder().encode("12345"))
//                .authorities("read", "write")
//                .build();
//
//        var u3 = User.builder()
//                .username("super")
//                .password(getPasswordEncoder().encode("12345"))
//                .authorities("read", "write" ,"delete")
//                .build();
//
//
//        uds.createUser(u1);
//        uds.createUser(u2);
//        uds.createUser(u3);
//        return uds;
//    }