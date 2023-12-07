package com.example.User.Repository;

import com.example.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Repo extends JpaRepository<User,Long> {

    public Optional<User> findByContacts(String contacts);
    public Optional<User> findByPasswordAndUsername(String password, String username);

}
