package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.User;
@Repository
public interface UserRepo extends JpaRepository<User,String> {

    Optional<User> findByuserEmail(String email);
    //extra methods db related
    //custom query methods
    //custom finder methods
    
    
}
