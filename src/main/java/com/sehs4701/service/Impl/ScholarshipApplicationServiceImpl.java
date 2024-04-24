package com.sehs4701.service.Impl;

import com.sehs4701.entity.ScholarshipApplication;
import com.sehs4701.service.ScholarshipApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScholarshipApplicationServiceImpl implements ScholarshipApplicationService {
    @Override
    public List<ScholarshipApplication> getAllApplicationByUserId(Long userId) {
        //  TODO: 1. validation, only School Scholarship Committee can view all application form
        //   2. get all data from db and return data
        return null;
    }

    @Override
    public ScholarshipApplication getApplicationByUserId(String userId) {
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
