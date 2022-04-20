package com.deenn.springbootblogrestapi.security;

import com.deenn.springbootblogrestapi.entity.Role;
import com.deenn.springbootblogrestapi.entity.User;
import com.deenn.springbootblogrestapi.repositories.UserRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
//@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
       User user  = userRepository.findByEmailOrUsername(usernameOrEmail, usernameOrEmail)
               .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email" + usernameOrEmail));
       return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
               mapRolesToAuthorities(user.getRoles()));

    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList();
    }
}
