package com.example.User.Service;

import com.example.User.Entity.Contacts;
import com.example.User.Repository.contactsRepository;
import com.example.User.Entity.User;
import com.example.User.Entity.UserDto;
import com.example.User.Repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements com.example.User.Service.Service {

    @Autowired
    private Repo repo;

    @Autowired
    private contactsRepository repoContact;

    @Override
    public String registerUser(UserDto userDto) {

        User user = new User(userDto.getUserName(),userDto.getContacts(),userDto.getPassword());
        repo.save(user);
        return "User registered";

    }

    @Override
    public String login(String password, String userName) {

        if(repo.findByPasswordAndUsername(password,userName).isPresent())
        {
            return "Logged in";
        }
        return "incorrect credential";
    }
}
