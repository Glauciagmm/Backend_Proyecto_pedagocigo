package com.uniquecare.pedagogico_backend.repositories;

import com.uniquecare.pedagogico_backend.models.ERole;
import com.uniquecare.pedagogico_backend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(ERole name);
}
