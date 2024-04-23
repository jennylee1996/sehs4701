package com.sehs4701.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "applications")
@Data
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "scholarship_id")
    private Scholarship scholarship;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "submit_date")
    private Date submitDate;

    @Column(nullable = false)
    private String status;

    @Column(precision = 3, scale = 2)
    private BigDecimal gpa;

    @Column(length = 50)
    private String major;

}
