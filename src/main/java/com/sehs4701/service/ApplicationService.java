package com.sehs4701.service;

import com.sehs4701.entity.Application;
import com.sehs4701.entity.ResponseMessage;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApplicationService {

    List<Application> getAllApplicationByUserId();
    ResponseMessage<Application> updateApplication(Application applicationUpdate);
    Application getApplicationByUserId(String userId);


}