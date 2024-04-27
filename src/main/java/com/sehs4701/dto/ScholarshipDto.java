package com.sehs4701.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ScholarshipDto {

    private Integer scholarshipId;
    private String scholarshipName;
    private Integer amount;
    private Date startDate;
    private Date endDate;
    private Date announceDate;
    private int quota;
    private int usedQuota;
}
