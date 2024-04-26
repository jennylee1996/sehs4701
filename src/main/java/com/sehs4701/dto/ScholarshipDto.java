package com.sehs4701.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ScholarshipDto {

    private Integer scholarshipId;
    private String scholarshipName;
    private BigDecimal quota;
}
