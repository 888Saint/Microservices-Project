package com.microservices.user.service.User.Service.repository;

import com.microservices.user.service.User.Service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
