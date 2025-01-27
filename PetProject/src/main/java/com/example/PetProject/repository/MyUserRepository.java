package com.example.PetProject.repository;

import com.example.PetProject.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, String> {
    Optional<MyUser> findByEmail(String email);
}
