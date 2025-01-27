package com.example.PetProject.repository;

import com.example.PetProject.entity.Permission;
import com.example.PetProject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
