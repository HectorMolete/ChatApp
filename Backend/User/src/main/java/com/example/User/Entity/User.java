package com.example.User.Entity;

import com.example.User.Chats.Chats;
import com.example.User.Contacts.Contacts;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(name = "user_chats",
            joinColumns = @JoinColumn(name="user-id"),
            inverseJoinColumns = @JoinColumn(name ="chat_id"))
    private Set<Chats> chats = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_contacts",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="contact_id")
    )
    private Set<Contacts> contact = new HashSet<>();
}
