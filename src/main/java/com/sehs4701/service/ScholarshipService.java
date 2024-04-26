package com.sehs4701.service;

import com.sehs4701.dto.ScholarshipDto;
import com.sehs4701.entity.Application;
import com.sehs4701.entity.ResponseMessage;
import com.sehs4701.entity.Scholarship;

import java.util.List;

public interface ScholarshipService {

    List<Scholarship> getAllScholarship();
    ResponseMessage<Scholarship> createScholarship(Scholarship scholarshipCreate);
    ResponseMessage<Scholarship> updateScholarship(ScholarshipDto scholarshipDto);
    void cancelScholarship(Integer scholarshipId);
}
