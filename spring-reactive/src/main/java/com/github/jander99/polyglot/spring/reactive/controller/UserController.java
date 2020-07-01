package com.github.jander99.polyglot.spring.reactive.controller;

import com.github.jander99.polyglot.spring.reactive.entity.User;
import com.github.jander99.polyglot.spring.reactive.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path="/users", method= RequestMethod.GET)
    public ResponseEntity<Flux<User>> getAllUsers() {
        return ResponseEntity.of(Optional.of(userService.getAllUsers()));
    }

    @RequestMapping(path = "/users/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Mono<User>> getSingleUser(@PathVariable("userId") Integer userId) {
        return ResponseEntity.of(Optional.of(userService.getSingleUser(userId)));
    }

}
