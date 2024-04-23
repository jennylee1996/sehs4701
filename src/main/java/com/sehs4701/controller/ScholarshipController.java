package com.sehs4701.controller;

import com.sehs4701.model.ResponseMessage;
import com.sehs4701.model.Scholarship;
import com.sehs4701.model.User;
import com.sehs4701.service.ScholarshipService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scholarship")
@AllArgsConstructor
public class ScholarshipController {
    private ScholarshipService scholarshipService;
    @GetMapping("/getAllScholarship")
    public ResponseEntity<ResponseMessage<List<Scholarship>>> getAllScholarship() {
        try {
            List<Scholarship> scholarshipList = scholarshipService.getAllScholarship();
            return ResponseEntity.ok(new ResponseMessage<>(true, "Get scholarship list successfully", scholarshipList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage<>(false, e.getMessage()));
        }
    }
}
