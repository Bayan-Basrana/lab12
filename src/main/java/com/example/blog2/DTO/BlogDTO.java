package com.example.blog2.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BlogDTO {
    public Integer id;
    private String title;

    private String body;

    private String username;
}
