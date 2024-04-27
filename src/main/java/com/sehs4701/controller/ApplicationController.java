package com.sehs4701.controller;

import com.sehs4701.dto.ApplicationDto;
import com.sehs4701.entity.Application;
import com.sehs4701.entity.ResponseMessage;
import com.sehs4701.entity.User;
import com.sehs4701.service.ApplicationService;
import com.sehs4701.service.AuthorizationService;
import com.sehs4701.service.UserService;
import com.sehs4701.vo.ApplicationVo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api/application")
@AllArgsConstructor
public class ApplicationController {

    private AuthorizationService authorizationService;
    private UserService userService;
    private ApplicationService applicationService;

    @GetMapping("/getAllApplication")
    public ResponseEntity<?> getAllApplication() {
        try {
            List<ApplicationVo> applications = applicationService.getAllApplication();
            return ResponseEntity
                    .ok(new ResponseMessage<>(true, "Get Applications Successfully", applications));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage<>(false, "Error get applications: " + e.getMessage()));
        }
    }

    @GetMapping("/getAllApplicationByUserId/{userId}")
    public ResponseEntity<?> getAllApplicationByUserId(@PathVariable Integer userId) {
        try {
            User user = userService.getUserById(userId);
            ResponseEntity<?> checkUserPermission = authorizationService.checkUserPermission(user, "Student");

            List<ApplicationVo> applications = applicationService.getAllApplicationByUserId(userId);
            return Objects.requireNonNullElseGet(checkUserPermission, () -> ResponseEntity
                    .ok(new ResponseMessage<>(true, "Get Applications Successfully", applications)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage<>(false, "Error updating application: " + e.getMessage()));
        }
    }

    // Approve & Reject
    @PostMapping("/updateApplication")
    public ResponseEntity<?> updateApplication(@RequestBody Application applicationUpdate) {
        try {
            ResponseMessage<Application> response = applicationService.updateApplication(applicationUpdate);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage<>(false, "Error updating application: " + e.getMessage()));
        }
    }

    @PostMapping("/createApplication")
    public ResponseEntity<?> createApplication(@RequestBody ApplicationDto applicationDto) {
        try {
            applicationDto.setSubmitDate(new Date());
            ResponseMessage<Application> response = applicationService.createApplication(applicationDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage<>(false, "Error creating application: " + e.getMessage()));
        }
    }

}
