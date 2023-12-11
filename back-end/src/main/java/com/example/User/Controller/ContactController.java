package com.example.User.Controller;

import com.example.User.Service.ContactServiceImpl;
import com.example.User.Repository.contactsRepository;
import com.example.User.Entity.Contacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class ContactController {

    @Autowired
    private ContactServiceImpl service;
    @Autowired
    private contactsRepository contactRepo;

    @PostMapping("/add-contact")
    public String AddContacts(@RequestBody Contacts contacts){

        if(contactRepo.findByContactNumber(contacts.getContactNumber()).isPresent())
        {
            throw new IllegalStateException("User exists");
        }
        service.addContact(contacts);
        return "User registered";
    }
}
