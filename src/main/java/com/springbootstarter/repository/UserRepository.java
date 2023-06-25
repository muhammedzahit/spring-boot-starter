package com.springbootstarter.repository;

import java.util.Optional;

import com.springbootstarter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsernameInfo(String usernameInfo);

}
