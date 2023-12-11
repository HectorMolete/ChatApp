package com.example.User.Repository;

import com.example.User.Entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface contactsRepository extends JpaRepository<Contacts,Long> {

    public Optional<Contacts> findByContactNumber(String contactNumber);

}
