package com.example.User.Contacts;

import com.example.User.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long contactId;

    private String contactNumber;
    private String contactName;

    @JsonIgnore
    @ManyToMany(mappedBy = "contact")
    private Set<User> userList = new HashSet<>();

    public Contacts(String contactName, String contactNumber) {

        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }
}
