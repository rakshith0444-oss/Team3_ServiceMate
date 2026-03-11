package com.servicemate.servicemate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.servicemate.servicemate.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}