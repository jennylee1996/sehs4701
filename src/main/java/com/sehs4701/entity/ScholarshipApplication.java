package com.sehs4701.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class ScholarshipApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate submissionDate;
    private LocalDate deadline;
    private String status;  // PENDING, APPROVED, REJECTED
    private Double amountRequested;

//    @ManyToOne
//    @JoinColumn(name = "student_id", nullable = false)
//    private Student student;
}
