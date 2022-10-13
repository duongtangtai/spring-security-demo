package com.example.springsecurity.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;
    private String name;
    private String username;
    private String password;
    private Integer score;
    @ManyToMany
    @JoinTable(name = "USER_ROLE",
    joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
    private Set<Role> roles;
}
