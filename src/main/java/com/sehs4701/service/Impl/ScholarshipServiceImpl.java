package com.sehs4701.service.Impl;

import com.sehs4701.dto.ApplicationDto;
import com.sehs4701.dto.ScholarshipDto;
import com.sehs4701.entity.Application;
import com.sehs4701.entity.ResponseMessage;
import com.sehs4701.entity.Scholarship;
import com.sehs4701.entity.User;
import com.sehs4701.repositiory.ApplicationRepository;
import com.sehs4701.repositiory.ScholarshipRepository;
import com.sehs4701.service.ScholarshipService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ScholarshipServiceImpl implements ScholarshipService {

    private ScholarshipRepository scholarshipRepository;
    private ApplicationRepository applicationRepository;
    @Override
    public List<Scholarship> getAllScholarship() {
        return scholarshipRepository.findByStatus(true);
    }

    @Override
    public ResponseMessage<Scholarship> createScholarship(Scholarship scholarshipCreate) {
        if (scholarshipCreate == null) {
            throw new IllegalArgumentException("Scholarship should not be null");
        }
        return new ResponseMessage<>(true, "Application create successfully", scholarshipRepository.save(scholarshipCreate));
    }

    @Override
    public ResponseMessage<Scholarship> updateScholarship(ScholarshipDto scholarshipDto) {
        if (scholarshipDto == null || scholarshipDto.getScholarshipName() == null) {
            throw new IllegalArgumentException("Mandatory field should not be null");
        }
        Scholarship existingScholarship = scholarshipRepository.findById(scholarshipDto.getScholarshipId())
                .orElseThrow(() -> new EntityNotFoundException("Scholarship not found with ID: " + scholarshipDto.getScholarshipId()));

        // TODO: heavnt check the count of Quota with the approved count
        Scholarship scholarshipUpdate= new Scholarship();
        scholarshipUpdate.setId(scholarshipDto.getScholarshipId());
        scholarshipUpdate.setScholarshipName(scholarshipDto.getScholarshipName());
        scholarshipUpdate.setStartDate(existingScholarship.getStartDate());
        scholarshipUpdate.setEndDate(existingScholarship.getEndDate());
        scholarshipUpdate.setAnnounceDate(existingScholarship.getAnnounceDate());
        scholarshipUpdate.setQuota(scholarshipDto.getQuota());
        scholarshipUpdate.setUsedQuota(BigDecimal.valueOf(0));

        return new ResponseMessage<>(true, "Application updated successfully", scholarshipRepository.save(scholarshipUpdate));
    }

    @Override
    public void cancelScholarship(Integer scholarshipId) {
        Scholarship scholarship = scholarshipRepository.findById(scholarshipId)
                .orElseThrow(() -> new RuntimeException("Scholarship not found with id " + scholarshipId));
        scholarship.setStatus(false);
        scholarshipRepository.save(scholarship);

        List<Application> applications = applicationRepository.findByScholarshipId(scholarshipId);
        if (!applications.isEmpty()) {
            applications.forEach(application -> {
                application.setStatus("canceled");
            });
            applicationRepository.saveAll(applications);
        }

    }


}
