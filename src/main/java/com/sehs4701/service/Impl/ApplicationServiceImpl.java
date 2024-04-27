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
import com.sehs4701.vo.ApplicationVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationRepository applicationRepository;
    private ScholarshipRepository scholarshipRepository;
    private UserRepository userRepository;

    @Override
    public List<ApplicationVo> getAllApplication() {
        List<Application> applications = applicationRepository.findAll();
        List<ApplicationVo> applicationVos = applications.stream().map(this::convertToApplicationVo).collect(Collectors.toList());
        return applicationVos;
    }
    @Override
    public List<ApplicationVo> getAllApplicationByUserId(Integer userId) {
        List<Application> applications = applicationRepository.findByUserId(userId);
        List<ApplicationVo> applicationVos = applications.stream().map(this::convertToApplicationVo).collect(Collectors.toList());
        return applicationVos;
    }

    private ApplicationVo convertToApplicationVo(Application application) {
        ApplicationVo applicationVo = new ApplicationVo();
        applicationVo.setId(application.getId());
        applicationVo.setStudentName(application.getUser().getFirstName() + " " + application.getUser().getLastName());
        applicationVo.setEmail(application.getUser().getEmail());
        applicationVo.setStatus(application.getStatus());
        applicationVo.setGpa(application.getGpa());
        applicationVo.setMajor(application.getMajor());
        applicationVo.setScholarship(application.getScholarship());
        applicationVo.setSubmitDate(application.getSubmitDate());
        return applicationVo;
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
            if (scholarship.getUsedQuota() >= scholarship.getQuota()) {
                Scholarship scholarshipNew = scholarshipRepository.findById(application.getScholarship().getId()).orElseThrow(() -> new RuntimeException("Scholarship not found with id " + application.getScholarship().getId()));;
                scholarshipNew.setUsedQuota(scholarship.getUsedQuota() + 1);
                scholarshipRepository.save(scholarshipNew);
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
        int count = applicationRepository.countUserApprovedApplication(applicationDto.getScholarshipId(), applicationDto.getUserId());
        if(count > 0) {
            throw new RuntimeException("User already apply this Scholarship");
        }

        Application applicationCreate = new Application();
        applicationCreate.setScholarship(scholarship);
        applicationCreate.setUser(user);
        applicationCreate.setSubmitDate(new Date());
        applicationCreate.setGpa(applicationDto.getGpa());
        applicationCreate.setMajor(applicationDto.getMajor());
        applicationCreate.setStatus("pending");
        return new ResponseMessage<>(true, "Application create successfully", applicationRepository.save(applicationCreate));
    }

}
