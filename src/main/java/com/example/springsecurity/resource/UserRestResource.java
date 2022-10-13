package com.example.springsecurity.resource;

import com.example.springsecurity.aop.AopAuthorization;
import com.example.springsecurity.model.User;
import com.example.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRestResource {
    private final UserService service;

    @GetMapping
    @AopAuthorization(role = "ADMIN")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        return new ResponseEntity<>(service.save(user), HttpStatus.CREATED);
    }
    @PostMapping("/add-role")
    public ResponseEntity<?> addRoleToUser(@RequestParam("userId") Long userId, @RequestBody Set<Long> roleIds) {
        return new ResponseEntity<>(service.addRoleToUser(userId, roleIds), HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteRoleById(@RequestParam("userId") Long userId) {
        service.deleteById(userId);
        return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
    }

}
