package com.example.springsecurity;

import com.example.springsecurity.model.Role;
import com.example.springsecurity.model.User;
import com.example.springsecurity.service.RoleService;
import com.example.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringSecurityApplication implements CommandLineRunner {
    private final RoleService roleService;
    private final UserService userService;
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = User.builder().id(1L).name("Tăng Tài").score(8)
                .username("duongtangtai").password("1234")
                .build();
        User user2 = User.builder().id(2L).name("Mỹ Nhi").score(7)
                .username("tranmynhi").password("1234")
                .build();
        User user3 = User.builder().id(3L).name("Kim Ngân").score(6)
                .username("duongkimngan").password("1234")
                .build();
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        Role role1 = Role.builder().id(4L).name("ADMIN").build();
        Role role2 = Role.builder().id(5L).name("LEADER").build();
        Role role3 = Role.builder().id(6L).name("STAFF").build();
        roleService.save(role1);
        roleService.save(role2);
        roleService.save(role3);
        Set<Long> roleIds = new HashSet<>();
        roleIds.add(role1.getId());
        roleIds.add(role2.getId());
        userService.addRoleToUser(1L, roleIds);
    }
}
