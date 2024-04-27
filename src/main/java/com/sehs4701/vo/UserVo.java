package com.sehs4701.vo;

import lombok.Data;

@Data
public class UserVo {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String major;
    private String role;
}
