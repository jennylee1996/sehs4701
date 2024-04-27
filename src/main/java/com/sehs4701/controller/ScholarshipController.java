package com.sehs4701.controller;

import com.sehs4701.dto.ApplicationDto;
import com.sehs4701.dto.ScholarshipDto;
import com.sehs4701.entity.Application;
import com.sehs4701.entity.ResponseMessage;
import com.sehs4701.entity.Scholarship;
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

    @GetMapping("/getScholarshipById/{id}")
    public ResponseEntity<ResponseMessage<Scholarship>> getScholarshipById(@PathVariable Integer id) {
        try {
            Scholarship scholarship = scholarshipService.getScholarshipById(id);
            return ResponseEntity.ok(new ResponseMessage<>(true, "Get scholarship successfully", scholarship));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage<>(false, e.getMessage()));
        }
    }

    @PostMapping("/createScholarship")
    public ResponseEntity<?> createScholarship(@RequestBody Scholarship scholarshipCreate) {
        try {
            ResponseMessage<Scholarship> response = scholarshipService.createScholarship(scholarshipCreate);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage<>(false, "Error creating application: " + e.getMessage()));
        }
    }

    @PostMapping("/updateScholarship")
    public ResponseEntity<?> updateScholarship(@RequestBody ScholarshipDto scholarshipDto) {
        try {
            ResponseMessage<Scholarship> response = scholarshipService.updateScholarship(scholarshipDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage<>(false, "Error updating scholarship: " + e.getMessage()));
        }
    }

    @GetMapping("/deleteScholarship/{scholarshipId}")
    public ResponseEntity<?> cancelScholarship(@PathVariable Integer scholarshipId) {
        try {
            scholarshipService.cancelScholarship(scholarshipId);
            return ResponseEntity.ok(new ResponseMessage<>(true, "Scholarship delete successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage<>(false, "Error canceling scholarship: " + e.getMessage()));
        }
    }

}
