package com.example.User.Service;

import com.example.User.Entity.User;
import com.example.User.Repository.Repo;
import com.example.User.Repository.contactsRepository;
import com.example.User.Entity.Contacts;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ContactServiceImpl implements ContactService {

    @Autowired
    private contactsRepository repo;

    @Autowired
    private Repo userRepo;

    @Override
    public String addContact(Contacts contacts) {

        if(repo.findByContactNumber(contacts.getContactNumber()).isPresent())
        {
            return "Contact exists";
        }
        return null;
    }
}
