package com.sehs4701.service.Impl;

import com.sehs4701.dto.ApplicationDto;
import com.sehs4701.entity.Application;
import com.sehs4701.entity.ResponseMessage;
import com.sehs4701.entity.Scholarship;
import com.sehs4701.entity.User;
import com.sehs4701.repositiory.ApplicationRepository;
import com.sehs4701.repositiory.ScholarshipRepository;
import com.sehs4701.repositiory.UserRepository;
import com.sehs4701.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationRepository applicationRepository;
    private ScholarshipRepository scholarshipRepository;
    private UserRepository userRepository;

    @Override
    public List<Application> getAllApplicationByUserId() {
        return applicationRepository.findAll();
    }

    @Override
    public ResponseMessage<Application> updateApplication(Application applicationUpdate) {
        if (applicationUpdate == null || applicationUpdate.getId() == null) {
            throw new IllegalArgumentException("Mandatory field should not be null");
        }
        Application application = applicationRepository.findById(applicationUpdate.getId())
                .orElseThrow(() -> new IllegalArgumentException("Application not found with id: " + applicationUpdate.getId()));

        if ("approved".equals(applicationUpdate.getStatus())) {
            Scholarship scholarship = application.getScholarship();
            int countApproved = applicationRepository.countByScholarshipAndStatus(scholarship,"approved", application.getId());
            if (countApproved >= scholarship.getQuota().intValue()) {
                throw new IllegalArgumentException("Scholarship quota exceeded");
            }
        }
        application.setStatus(applicationUpdate.getStatus());
        applicationRepository.save(application);

        return new ResponseMessage<>(true, "Application updated successfully", application);
    }

    @Override
    public ResponseMessage<Application> createApplication(ApplicationDto applicationDto) {
        if (applicationDto == null) {
            throw new IllegalArgumentException("Mandatory field should not be null");
        }
        Scholarship scholarship = scholarshipRepository.findById(applicationDto.getScholarshipId()).orElseThrow(
                () -> new RuntimeException("Scholarship not found"));
        User user = userRepository.findById(applicationDto.getUserId()).orElseThrow(
                () -> new RuntimeException("User not found"));

        Application applicationCreate = new Application();
        applicationCreate.setScholarship(scholarship);
        applicationCreate.setUser(user);
        applicationCreate.setSubmitDate(applicationDto.getSubmitDate());
        applicationCreate.setGpa(applicationDto.getGpa());
        applicationCreate.setMajor(applicationDto.getMajor());
        applicationCreate.setStatus("pending");
        return new ResponseMessage<>(true, "Application create successfully", applicationRepository.save(applicationCreate));
    }

}
