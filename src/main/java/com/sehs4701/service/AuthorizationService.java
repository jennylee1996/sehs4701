package com.sehs4701.service;

import com.sehs4701.entity.User;
import org.springframework.http.ResponseEntity;


public interface AuthorizationService {

    ResponseEntity<?> checkUserPermission(User user, String requiredRole);

}
