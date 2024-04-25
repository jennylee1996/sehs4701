package com.sehs4701.controller;

import com.sehs4701.entity.ResponseMessage;
import com.sehs4701.entity.User;
import com.sehs4701.service.ApplicationService;
import com.sehs4701.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/application")
@AllArgsConstructor
public class ApplicationController {

    private UserService userService;
    private ApplicationService applicationService;
    // Approve & Reject
    //TODO: @PostMapping("/updateApplication")



    //TODO: @GetMapping("/getAllApplicationByUserId")
    @GetMapping("/getAllApplicationByUserId/{userId}")
    public ResponseEntity<?> getAllApplicationByUserId(@PathVariable Integer userId) {

        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage<>(false, "User not found"));
        }
        if (!"Committee".equals(user.getRole())) {
            return ResponseEntity
                    .ok(new ResponseMessage<>(false, "Student do not have permission to view all applications"));
        }
        return ResponseEntity
                .ok(new ResponseMessage<>(true, "Get Applications Successfully", applicationService.getAllApplicationByUserId()));
    }

}
