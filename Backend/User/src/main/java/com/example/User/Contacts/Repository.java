package com.example.User.Contacts;

import com.example.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Contacts,Long> {

    public Optional<Contacts> findByContactNumber(String contactNumber);

}
