package com.example.documenttranslate.repository;

import com.example.documenttranslate.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

}
