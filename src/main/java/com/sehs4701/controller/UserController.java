package com.sehs4701.controller;

import com.sehs4701.dto.UserDto;
import com.sehs4701.entity.ResponseMessage;
import com.sehs4701.entity.User;
import com.sehs4701.repositiory.UserRepository;
import com.sehs4701.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<ResponseMessage<String>> register(@RequestBody UserDto userDto) {
        try {
            // Check if the email is already registered
            if (userRepository.existsByEmail(userDto.getEmail())) {
                return ResponseEntity.badRequest().body(new ResponseMessage<>(false, "Email already registered"));
            }

            // Create a new User entity
            User user = new User();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setRole("Student"); // Assuming the default role is Student
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));

            // Save the user to the database
            userRepository.save(user);

            return ResponseEntity.ok(new ResponseMessage<>(true, "User registered successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage<>(false, "Failed to register user"));
        }
    }

    //TODO: @PostMapping("/login")

    //TODO: @PostMapping("/logout")

    @PostMapping("/getUserById")
    public ResponseEntity<ResponseMessage<User>> getUserById(@RequestParam Long userId) {

        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.ok(new ResponseMessage<>(false, "User not found"));
        }
        return ResponseEntity.ok(new ResponseMessage<>(true, "User retrieved successfully", user));
    }
}
