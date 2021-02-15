package com.embl.test.restfulmicroservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.embl.test.restfulmicroservice.config.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}