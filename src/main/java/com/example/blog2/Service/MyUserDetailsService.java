package com.example.blog2.Service;

import com.example.blog2.Api.ApiException;
import com.example.blog2.Model.User;
import com.example.blog2.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final AuthRepository authRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= authRepository.findUserByUsername(username);
        if (user==null){
            throw new ApiException("wrong username or password");
        }

        return user;
    }
}
