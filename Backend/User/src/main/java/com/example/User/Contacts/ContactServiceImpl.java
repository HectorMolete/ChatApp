package com.example.User.Contacts;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ContactServiceImpl implements ContactService {

    @Autowired
    private Repository repo;

    @Override
    public String addContact(Contacts contacts) {

        Contacts contact = new Contacts(contacts.getContactName(), contacts.getContactNumber());
        repo.save(contact);
        return "User saved";
    }
}
