package com.example.blog2.Controller;

import com.example.blog2.Api.ApiResponse;
import com.example.blog2.Model.Blog;
import com.example.blog2.Model.User;
import com.example.blog2.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;


    //all
@GetMapping("/getAll")
    public ResponseEntity getAllBlogs (){
        return ResponseEntity.status(200).body(blogService.getAllBlogs());
    }


    //user
@GetMapping("/getAllMyBlogs")
    public ResponseEntity getAllMyBlogs (@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(blogService.getAllMyBlogs(user.getId()));
    }


//user
    @PostMapping("/add")
    public ResponseEntity addBlog (@AuthenticationPrincipal User user , @RequestBody @Valid Blog blog){
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.status(200).body(new ApiResponse("blog added successfully"));
    }


//user
    @PutMapping("/update/{blog_id}")
    public ResponseEntity update (@AuthenticationPrincipal User user, @PathVariable Integer blog_id, @RequestBody @Valid Blog blog){
        blogService.update(user.getId(), blog_id,blog);
        return ResponseEntity.status(200).body(new ApiResponse("update successfully"));
    }

    //user
    @DeleteMapping("/delete/{blog_id}")
    public ResponseEntity delete (@AuthenticationPrincipal User user ,@PathVariable Integer blog_id ){
        blogService.delete(user.getId(), blog_id);
        return ResponseEntity.status(200).body(new ApiResponse("deleted successfully"));
    }

    //all
    @GetMapping("/get-blog-by-title")
    public ResponseEntity getBlogByTitle (@RequestBody String title){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title));
    }

    //All
    @GetMapping("/get-blog-by-id/{id}")
    public ResponseEntity getBlogById (@PathVariable Integer id){
        return ResponseEntity.status(200).body(blogService.getBlogById(id));
    }

}
