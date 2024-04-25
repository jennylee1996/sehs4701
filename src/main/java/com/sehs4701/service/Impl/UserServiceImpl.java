package com.sehs4701.service.Impl;

import com.sehs4701.entity.User;
import com.sehs4701.repositiory.UserRepository;
import com.sehs4701.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Override
    public User getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }
}
