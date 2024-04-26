package com.sehs4701.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "scholarships")
@Data
public class Scholarship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "scholarship_name", nullable = false, length = 50)
    private String scholarshipName;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "announce_date")
    private Date announceDate;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal quota;

    @Column(name = "used_quota", precision = 10, scale = 2)
    private BigDecimal usedQuota;

    @Column(name = "status")
    private boolean status = true;
}
