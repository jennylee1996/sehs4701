package com.sehs4701.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ScholarshipQuotas")
@Data
public class ScholarshipQuotas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;
    private int yearlyQuota;
}
