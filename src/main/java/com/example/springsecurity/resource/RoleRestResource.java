package com.example.springsecurity.resource;

import com.example.springsecurity.aop.AopAuthorization;
import com.example.springsecurity.model.Role;
import com.example.springsecurity.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleRestResource {
    private final RoleService service;

    @GetMapping
    @AopAuthorization(role = "ADMIN")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Role role) {
        return new ResponseEntity<>(service.save(role), HttpStatus.CREATED);
    }

}
