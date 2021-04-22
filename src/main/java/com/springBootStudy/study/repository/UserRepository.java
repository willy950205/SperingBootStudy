package com.springBootStudy.study.repository;

import com.springBootStudy.study.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
