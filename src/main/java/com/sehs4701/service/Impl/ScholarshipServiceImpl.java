package com.sehs4701.service.Impl;

import com.sehs4701.entity.Scholarship;
import com.sehs4701.repositiory.ScholarshipRepository;
import com.sehs4701.service.ScholarshipService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScholarshipServiceImpl implements ScholarshipService {

    private ScholarshipRepository scholarshipRepository;
    @Override
    public List<Scholarship> getAllScholarship() {
        List<Scholarship> scholarshipList = scholarshipRepository.findAll();
        return scholarshipList;
    }
}
