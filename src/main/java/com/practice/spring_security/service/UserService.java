package com.practice.spring_security.service;

import com.practice.spring_security.model.UserPrinciple;
import com.practice.spring_security.model.Userss;
import com.practice.spring_security.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    private UsersRepo uRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Userss user = uRepo.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrinciple(user);
    }

    public Userss saveUser(Userss user){
        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        return uRepo.save(user);
    }

    public Userss findByUsername(String username){
        return uRepo.findUserByUsername(username);
    }
}
