package com.example.crud.repositories;
import com.example.crud.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


public interface UserRepository extends JpaRepository<User, String> {

    UserDetails findByLogin(String login);
  
    
}
