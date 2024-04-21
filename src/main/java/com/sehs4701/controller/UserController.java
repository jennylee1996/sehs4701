package com.sehs4701.controller;

import com.sehs4701.model.ResponseMessage;
import com.sehs4701.model.User;
import com.sehs4701.service.ScholarshipApplicationService;
import com.sehs4701.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/getUserById")
    public ResponseEntity<ResponseMessage<User>> getUserById(@RequestParam Long userId) {

        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.ok(new ResponseMessage<>(false, "User not found"));
        }
        return ResponseEntity.ok(new ResponseMessage<>(true, "User retrieved successfully", user));
    }
}
