package com.sehs4701.controller;

import com.sehs4701.dto.LoginDto;
import com.sehs4701.dto.RegisterDto;
import com.sehs4701.entity.ResponseMessage;
import com.sehs4701.entity.User;
import com.sehs4701.repositiory.UserRepository;
import com.sehs4701.service.UserService;
import com.sehs4701.vo.UserVo;
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
    public ResponseEntity<ResponseMessage<String>> register(@RequestBody RegisterDto registerDto) {
        try {
            // Check if the email is already registered
            if (userRepository.existsByEmail(registerDto.getEmail())) {
                return ResponseEntity.badRequest().body(new ResponseMessage<>(false, "Email already registered"));
            }

            // Create a new User entity
            User user = new User();
            user.setFirstName(registerDto.getFirstName());
            user.setLastName(registerDto.getLastName());
            user.setEmail(registerDto.getEmail());
            user.setMajor(registerDto.getMajor());
            user.setRole("Student"); // Assuming the default role is Student
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

            // Save the user to the database
            userRepository.save(user);

            return ResponseEntity.ok(new ResponseMessage<>(true, "User registered successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage<>(false, "Failed to register user"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseMessage<UserVo>> login(@RequestBody LoginDto loginDto) {
        try {
            // Find the user by email
            User user = userRepository.findByEmail(loginDto.getEmail());

            if (user == null) {
                return ResponseEntity.badRequest().body(new ResponseMessage<>(false, "Invalid credentials"));
            }

            // Check the password
            if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                return ResponseEntity.badRequest().body(new ResponseMessage<>(false, "Invalid credentials"));
            }

            // Generate a token for the authenticated user
            //String token = userService.generateToken(user);
            UserVo userVo = new UserVo();
            userVo.setId(user.getId());
            userVo.setFirstName(user.getFirstName());
            userVo.setLastName(user.getLastName());
            userVo.setEmail(user.getEmail());
            userVo.setMajor(user.getMajor());
            userVo.setRole(user.getRole());

            return ResponseEntity.ok(new ResponseMessage<>(true, "Login successful", userVo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage<>(false, "Failed to login"));
        }
    }

    @PostMapping("/getUserById")
    public ResponseEntity<ResponseMessage<User>> getUserById(@RequestParam Integer userId) {

        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.ok(new ResponseMessage<>(false, "User not found"));
        }
        return ResponseEntity.ok(new ResponseMessage<>(true, "User retrieved successfully", user));
    }
}
