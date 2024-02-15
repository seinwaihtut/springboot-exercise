package com.springboot.exercise.services;

import com.springboot.exercise.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User create(User user);
    List<User> getAll();

}
