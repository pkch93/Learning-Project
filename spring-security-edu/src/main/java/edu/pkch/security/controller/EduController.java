package edu.pkch.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("test")
public class EduController {

    @GetMapping
    public String test(Principal principal) {
        log.info("login user: {}", principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        authorities.forEach(authority -> log.info("{}", authority));

        return "hello";
    }
}
