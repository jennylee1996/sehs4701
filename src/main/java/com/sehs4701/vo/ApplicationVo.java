package com.sehs4701.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sehs4701.entity.Scholarship;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ApplicationVo {
    private Integer id;
    private String studentName;
    private String email;
    private Scholarship scholarship;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date submitDate;
    private String status;
    private BigDecimal gpa;
    private String major;

}
