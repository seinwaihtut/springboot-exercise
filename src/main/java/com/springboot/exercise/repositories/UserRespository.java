package com.springboot.exercise.repositories;

import com.springboot.exercise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends JpaRepository<User, Long> {
    User findByEmail(String username);
}
