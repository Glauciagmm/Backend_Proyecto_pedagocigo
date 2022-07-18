package com.uniquecare.pedagogico_backend.repositories;

import com.uniquecare.pedagogico_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User findUserById(Long id);
    User getUserByUsername(String username);
}
