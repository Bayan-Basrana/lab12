package com.example.blog2.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @NotEmpty(message = " title is empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String title;

    @NotEmpty(message = " body is empty")
    @Column(columnDefinition = "varchar(200) not null")
    private String body;

    @ManyToOne
    @JsonIgnore
    private User user;
}
