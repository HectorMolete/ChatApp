package com.example.User.Service;

import com.example.User.Entity.User;
import com.example.User.Repository.Repo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.singletonList;

// User model class
@Entity
public abstract class UserDetailsService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    public abstract UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    // Other user details

    // Getters and setters
}

