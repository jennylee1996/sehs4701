package com.sehs4701.service;

import com.sehs4701.entity.Application;

import java.util.List;

public interface ApplicationService {

    List<Application> getAllApplicationByUserId();
    Application getApplicationByUserId(String userId);
    String createApplicationByUserId(String userId);
    String modifyStatusInApplicationByUserId(String userId);

}
