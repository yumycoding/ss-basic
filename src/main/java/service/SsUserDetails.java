package service;

import entity.SsUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.Collection;

import static java.util.Arrays.stream;

@RequiredArgsConstructor
public class SsUserDetails implements UserDetails {


    private final SsUser ssUser;

    @Override
    public String getUsername() {
        return ssUser.getUserName();
    }

    @Override
    public String getPassword() {
        return ssUser.getUserPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Assert.notNull(ssUser.getAuthorities(), "null");
        return stream(ssUser.getAuthorities()).map(SimpleGrantedAuthority::new).toList();

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return ssUser.getNotLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ssUser.getNotLocked();
    }
}
