package com.example.blog2.Service;

import com.example.blog2.Api.ApiException;
import com.example.blog2.Model.User;
import com.example.blog2.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public void register (User user){
        user.setRole("USER");
        String hashPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPassword);
        authRepository.save(user);
    }

public List<User> getAllUser (){
      return   authRepository.findAll();
}


public void update (Integer user_id ,User user1){
        User old =authRepository.findUserById(user_id);
        if (!old.getId().equals(user1.getId())){
            throw new ApiException("you not allow too update ");
        }
        old.setUsername(user1.getUsername());
        old.setPassword(user1.getPassword());
        authRepository.save(old);
}

public void delete (Integer user_id  ){
    User user = authRepository.findUserById(user_id);
    if (user ==null){
        throw new ApiException("user not found");
    }

    authRepository.delete(user);
}
}
