package com.sehs4701.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Column(name = "amount")
    private Integer amount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Column(name = "start_date")
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Column(name = "end_date")
    private Date endDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Column(name = "announce_date")
    private Date announceDate;

    @Column(nullable = false)
    private int quota;

    @Column(name = "used_quota", insertable = false)
    private int usedQuota;

    @Column(name = "status")
    private boolean status = true;
}
