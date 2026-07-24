package com.example.enotes_api_service.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    @Column(name = "mobile")
    private String mobNo;
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",                              // Links to your 'user_role' table
            joinColumns = @JoinColumn(name = "user_id"),     // Maps user foreign key
            inverseJoinColumns = @JoinColumn(name = "role_id") // Maps role foreign key
    )
    private List<Role> roles;
    @OneToOne(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name= "status_id")
    private AccountStatus status;
}
