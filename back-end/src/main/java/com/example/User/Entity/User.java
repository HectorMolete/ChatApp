package com.example.User.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long  Id;

    private String username;
    private String contacts;
    private String password;

    public User(String username, String contacts, String password) {
        this.username = username;
        this.password = password;
        this.contacts = contacts;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Chats> chats;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Contacts> contact = new ArrayList<>();
}
