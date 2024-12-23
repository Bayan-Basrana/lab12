package com.example.blog2.Service;

import com.example.blog2.Api.ApiException;
import com.example.blog2.DTO.BlogDTO;
import com.example.blog2.Model.Blog;
import com.example.blog2.Model.User;
import com.example.blog2.Repository.AuthRepository;
import com.example.blog2.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

public List<BlogDTO> getAllBlogs (){
    return convertBlogToDTO(blogRepository.findAll());
}

    public List<Blog> getAllMyBlogs (Integer user_id){
        User user = authRepository.findUserById(user_id);
        if (user==null){
            throw new ApiException("wrong user id");
        }
        return blogRepository.findAllByUser(user);
    }


    public void addBlog (Integer user_id , Blog blog){
        User user = authRepository.findUserById(user_id);
        if (user==null){
            throw new ApiException("wrong user id");
        }
        blog.setUser(user);
        blogRepository.save(blog);

    }

    public void update (Integer user_id , Integer blog_id , Blog blog){
        User user = authRepository.findUserById(user_id);
        if (user==null){
            throw new ApiException("wrong user id");
        }
        Blog old = blogRepository.findBlogById(blog_id);
        if (old==null){
            throw new ApiException("blog not found");
        }
        if (!old.getUser().getId().equals(user.getId())){
            throw new ApiException("blog not yours");
        }
        old.setTitle(blog.getTitle());
        old.setBody(blog.getBody());
        blogRepository.save(old);
    }


    public void delete (Integer user_id , Integer blog_id ){
        User user = authRepository.findUserById(user_id);
        if (user==null){
            throw new ApiException("wrong user id");
        }
        Blog blog = blogRepository.findBlogById(blog_id);
        if (blog==null){
            throw new ApiException("blog not found");
        }
        if (!blog.getUser().getId().equals(user.getId())){
            throw new ApiException("blog not yours");
        }
        blogRepository.delete(blog);
    }


    public BlogDTO getBlogByTitle (String title){
        Blog blog = blogRepository.findBlogByTitle(title);
                if (blog== null){
                    throw  new ApiException("blog not found");
                }

                return new BlogDTO(blog.getId(), blog.getTitle(), blog.getBody(), blog.getUser().getUsername());
    }

    public BlogDTO getBlogById (Integer id){
        Blog blog = blogRepository.findBlogById(id);
        if (blog== null){
            throw  new ApiException("blog not found");
        }
        return new BlogDTO(blog.getId(), blog.getTitle(), blog.getBody(), blog.getUser().getUsername());
    }


    public  List<BlogDTO> convertBlogToDTO(Collection<Blog> blogs){
        List<BlogDTO> blogDTOS = new ArrayList<>();
        for (Blog b : blogs){
            blogDTOS.add(new BlogDTO(b.getId(),b.getTitle(),b.getBody(),b.getUser().getUsername()));
        }
        return blogDTOS;
    }
}
