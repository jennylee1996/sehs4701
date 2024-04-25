package com.sehs4701.service.Impl;

import com.sehs4701.entity.Application;
import com.sehs4701.repositiory.ApplicationRepository;
import com.sehs4701.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationRepository applicationRepository;

    @Override
    public List<Application> getAllApplicationByUserId() {
        return applicationRepository.findAll();
    }

    @Override
    public Application getApplicationByUserId(String userId) {
        // TODO 1. validation, only student can view his/her own application in this year
        //  2. search data and return
        return null;
    }

    @Override
    public String createApplicationByUserId(String userId) {
        // TODO 1. validation, only student can create application
        //  2. create success message
        return null;
    }

    @Override
    public String modifyStatusInApplicationByUserId(String userId) {
        // TODO 1. validation, only staff can modify status in application , check the amount of quotas
        //  2. modify the status and get the return message
        return null;
    }
}
