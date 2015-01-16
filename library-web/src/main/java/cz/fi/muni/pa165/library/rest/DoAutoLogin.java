package cz.fi.muni.pa165.library.rest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class DoAutoLogin {

    public  static void doIt() {
        List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
        auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        Authentication auth = new UsernamePasswordAuthenticationToken("rest", null, auths);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
