package me.melyukhov.SpringProject.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface LoginService {
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException;
}
