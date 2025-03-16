package com.megacity.cab.repository;

import com.megacity.cab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
