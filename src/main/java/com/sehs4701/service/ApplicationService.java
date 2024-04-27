package com.sehs4701.service;

import com.sehs4701.dto.ApplicationDto;
import com.sehs4701.entity.Application;
import com.sehs4701.entity.ResponseMessage;
import com.sehs4701.vo.ApplicationVo;

import java.util.List;

public interface ApplicationService {

    List<ApplicationVo> getAllApplication();
    List<ApplicationVo> getAllApplicationByUserId(Integer userId);
    ResponseMessage<Application> updateApplication(Application applicationUpdate);
    ResponseMessage<Application> createApplication(ApplicationDto applicationDto);
}
