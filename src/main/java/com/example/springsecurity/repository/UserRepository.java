package com.example.springsecurity.repository;

import com.example.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
    @Query(value = "select u from User u left join u.roles r where u.username = ?1 and r.name = ?2")
    Optional<User> findByUsernameAndRoleName(String username, String roleName);
}
