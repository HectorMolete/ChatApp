package com.example.User.Service;

import com.example.User.Entity.User;
import com.example.User.Repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.singletonList;

// UserDetailsService implementation
@Service
public class UserDetailsServiceImpl extends UserDetailsService {

    @Autowired
    private Repo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
    }

    private List<GrantedAuthority> getAuthority() {
        return singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
