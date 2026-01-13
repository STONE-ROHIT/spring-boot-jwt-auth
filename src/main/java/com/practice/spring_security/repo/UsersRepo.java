package com.practice.spring_security.repo;

import com.practice.spring_security.model.Userss;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Userss, String>{

    Userss findUserByUsername(String username);

}