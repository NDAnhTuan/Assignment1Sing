package com.example.PetProject.dto.response;

import com.example.PetProject.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyUserResponse {
    String id;
    String email;
    String firstName;
    String lastName;
    LocalDate dob;
    Set<RoleResponse> roles;
}
