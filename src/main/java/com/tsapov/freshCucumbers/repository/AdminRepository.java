package com.tsapov.freshCucumbers.repository;

import com.tsapov.freshCucumbers.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);
}