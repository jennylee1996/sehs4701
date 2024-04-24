package com.sehs4701.controller;

import com.sehs4701.entity.User;
import com.sehs4701.service.ScholarshipApplicationService;
import com.sehs4701.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/application")
@AllArgsConstructor
public class ScholarshipApplicationController {


    private UserService userService;
    private ScholarshipApplicationService scholarshipApplicationService;


    @PostMapping("/getAllApplicationByUserId")
    public ResponseEntity<?> getAllApplicationByUserId(@RequestParam Long userId) {

        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity
                    .badRequest()
                    .body("User not found");
        }
        if ( user.getRole().equals("Student")) {
            return ResponseEntity
                    .ok()
                    .body("Student do not have permission to view all application.");
        }
        return  ResponseEntity
                .ok()
                .body(scholarshipApplicationService.getAllApplicationByUserId(userId));
    }

}
