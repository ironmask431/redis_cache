package org.example.redis_cache.controller;

import lombok.RequiredArgsConstructor;
import org.example.redis_cache.domain.User;
import org.example.redis_cache.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public User registUser( @RequestBody User req) {
        return userService.registUser(req);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User req) {
        req.setId(id);
        return userService.updateUser(req);
    }
}
