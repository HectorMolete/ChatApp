package com.example.User.Entity;

import com.example.User.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Chats {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long chatId;

    private String Chats;
    private String HighlightedChats;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}