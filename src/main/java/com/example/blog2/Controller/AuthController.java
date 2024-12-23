package com.example.blog2.Controller;

import com.example.blog2.Api.ApiResponse;
import com.example.blog2.Model.Blog;
import com.example.blog2.Model.User;
import com.example.blog2.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


//All
    @PostMapping("/register")
    public ResponseEntity register (@RequestBody @Valid User user){
        authService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("user added successfully"));
    }

    //Admin
    @GetMapping("/getAllUser")
    public ResponseEntity getAllUser (){
        return ResponseEntity.status(200).body(authService.getAllUser());
    }

    //user
    @PutMapping("/update")
    public ResponseEntity update (@AuthenticationPrincipal User user, @RequestBody @Valid User user1){
        authService.update(user.getId(),user1);
        return ResponseEntity.status(200).body(new ApiResponse("update successfully"));
    }

    //Admin
    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity delete (@PathVariable Integer user_id){
        authService.delete(user_id);
        return ResponseEntity.status(200).body(new ApiResponse("deleted successfully"));
    }

}
