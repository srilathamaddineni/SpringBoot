package com.example.SpringSecEx.repo;

import com.example.SpringSecEx.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

//    Users findByUserName(String username);

    Users findByEmail(String email);
}
