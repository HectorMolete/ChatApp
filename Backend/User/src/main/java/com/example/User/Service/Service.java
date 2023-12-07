package com.example.User.Service;

import com.example.User.Entity.User;
import com.example.User.Entity.UserDto;


public interface Service {

    public String registerUser(UserDto userDto);

    public String login(String password, String username);


    User assignContact(Long id, Long contactId);


}
