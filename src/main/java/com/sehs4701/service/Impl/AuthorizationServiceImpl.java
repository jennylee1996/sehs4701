package com.sehs4701.service.Impl;

import com.sehs4701.entity.ResponseMessage;
import com.sehs4701.entity.User;
import com.sehs4701.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    public boolean hasPermission(User user,String requiredRole) {
        return user != null && requiredRole.equals(user.getRole());
    }
    @Override
    public ResponseEntity<?> checkUserPermission(User user, String requiredRole) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage<>(false, "User not found"));
        }
        if (!hasPermission(user, requiredRole)) {
            return ResponseEntity
                    .ok(new ResponseMessage<>(false, "User do not have permission"));
        }
        return null;
    }
}
