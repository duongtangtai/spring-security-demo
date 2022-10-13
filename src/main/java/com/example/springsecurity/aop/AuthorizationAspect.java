package com.example.springsecurity.aop;

import com.example.springsecurity.exception.MyException;
import com.example.springsecurity.model.User;
import com.example.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;
import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthorizationAspect {
    private final UserRepository userRepository;

    @Before("@annotation(aopAuthorization)")
    public void authorization(AopAuthorization aopAuthorization) throws ValidationException {
        String role = aopAuthorization.role();
        String username = getUsername();
        System.out.println("roleName: " + role);
        System.out.println("username: " + username);
        if (!isUserRoleValid(username, role)) {
            System.out.println("username is not valid");
            throw new MyException("Username is not valid");
        }
    }

    private boolean isUserRoleValid(String username, String role) {
        Optional<User> user = userRepository.findByUsernameAndRoleName(username, role);
        return user.isPresent();
    }

    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        if (authentication.getPrincipal() instanceof String principal) {
            return principal;
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }
}
