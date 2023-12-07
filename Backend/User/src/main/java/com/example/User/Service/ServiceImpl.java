package com.example.User.Service;

import com.example.User.Contacts.Contacts;
import com.example.User.Contacts.Repository;
import com.example.User.Entity.User;
import com.example.User.Entity.UserDto;
import com.example.User.Repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ServiceImpl implements com.example.User.Service.Service {

    @Autowired
    private Repo repo;

    @Autowired
    private Repository repoContact;

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



    @Override
    public User assignContact(Long id, Long contactId) {

        Set<Contacts> contactSet = null;
        User user = repo.findById(id).get();
        Contacts contact = repoContact.findById(contactId).get();
        contactSet = user.getContact();
        contactSet.add(contact);

        return repo.save(user);
    }
}
