package com.example.enotes_api_service.config.security;

import com.example.enotes_api_service.entity.User;
import com.example.enotes_api_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    //end point authentication check will done here
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepo.findByEmail(username);
       if(user==null){
           throw new UsernameNotFoundException(" invalid email");
       }
       //if email exist then cteate a new object of customuserdetail(user)
        return new CustomUserDetails(user);
    }
}
