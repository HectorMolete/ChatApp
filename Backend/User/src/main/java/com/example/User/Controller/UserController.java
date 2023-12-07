package com.example.User.Controller;

import com.example.User.Entity.User;
import com.example.User.Entity.UserDto;
import com.example.User.Repository.Repo;
import com.example.User.Service.Service;
import com.example.User.Service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ServiceImpl service;

    @Autowired
    private Repo repo;


    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:4200/")
    public String register(@RequestBody UserDto userdto) {



        if(repo.findByContacts(userdto.getContacts()).isPresent())
        {
            throw new IllegalStateException("User exists");
        }
        return service.registerUser(userdto);

    }

    /*@GetMapping("/log-in/{Id}")
    public String logIn(@PathVariable long Id, @RequestBody String password, String userName)
    {
        Id =
        return null;
    }*/




    @PutMapping("/{Id}/{contactId}")
    public User assignUserToContacts(
            @PathVariable Long Id,
            @PathVariable Long contactId
    ){

               return service.assignContact(Id,contactId);
    }


}
