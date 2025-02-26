package com.scm.services;

import java.util.List;
import java.util.Optional;

import com.scm.entities.User;

public interface UserService {
    //saving user
     User saveUser(User user);
     //get by id
    Optional<User> getUserById(String id);

    Optional<User> updatUser(User user);

    void deleteUser(String id);

    boolean isUserExist(String id);


    List<User> getAllUsers();

    //add more methods here related to businss logic

     


    
}
