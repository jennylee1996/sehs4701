package com.sehs4701.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ApplicationDto {
    private Integer scholarshipId;
    private Integer userId;
    private Date submitDate;
    private BigDecimal gpa;
    private String major;

}
