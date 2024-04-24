package com.sehs4701.service;

import com.sehs4701.entity.ScholarshipApplication;

import java.util.List;

public interface ScholarshipApplicationService {

    List<ScholarshipApplication> getAllApplicationByUserId(Long userId);
    ScholarshipApplication getApplicationByUserId(String userId);
    String createApplicationByUserId(String userId);
    String modifyStatusInApplicationByUserId(String userId);

}
