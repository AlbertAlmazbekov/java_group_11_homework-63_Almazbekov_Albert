package com.example.micrigramm.controller;

import com.example.micrigramm.dto.UserProfileDTO;
import com.example.micrigramm.dto.UserRegistrationDTO;
import com.example.micrigramm.entity.User;
import com.example.micrigramm.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 36000)
@RestController
@RequestMapping("/users")  //http://localhost:8080/users
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        return userService.register(userRegistrationDTO);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Void> auth(Authentication authentication) {
        try {
            authentication.getPrincipal();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{userId}")
    ResponseEntity<UserProfileDTO> getUser(@PathVariable Long userId){
        return userService.getUser(userId);
    }
}
