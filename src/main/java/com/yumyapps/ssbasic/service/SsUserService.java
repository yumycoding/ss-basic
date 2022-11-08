package com.yumyapps.ssbasic.service;

import com.yumyapps.ssbasic.config.SsUserDetails;
import com.yumyapps.ssbasic.dao.SsUserRepository;
import com.yumyapps.ssbasic.entity.RegisterUserDto;
import com.yumyapps.ssbasic.entity.SsUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
@RequiredArgsConstructor
public class SsUserService implements UserDetailsService {

    private final SsUserRepository userRepository;

    //    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var ssUser = userRepository.findByUserName(username);
        return ssUser.map(SsUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    public SsUser registerNewUser(RegisterUserDto userDto) {

        userRepository.findByUserName(userDto.getUserName()).ifPresent(u -> {
            throw new EntityExistsException("userDto already exist" + u);
        });

        userDto.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));

        var user = new SsUser();
        user.setUserName(userDto.getUserName());
        user.setUserPassword(userDto.getUserPassword());
        user.setAuthorities(userDto.getAuthorities());
        user.setEnable(userDto.getEnable());
        user.setNotLocked(userDto.getNotLocked());
        return userRepository.save(user);
    }


    public SsUser readUserByName(String name) {
        return userRepository.findByUserName(name).orElseThrow(() -> new UsernameNotFoundException("user not " + name));
    }

}
