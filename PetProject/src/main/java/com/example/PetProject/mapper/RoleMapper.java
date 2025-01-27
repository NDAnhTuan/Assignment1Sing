package com.example.PetProject.mapper;

import com.example.PetProject.dto.request.PermissionRequest;
import com.example.PetProject.dto.response.PermissionResponse;
import com.example.PetProject.dto.request.RoleRequest;
import com.example.PetProject.dto.response.RoleResponse;
import com.example.PetProject.entity.Permission;
import com.example.PetProject.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    // Bỏ qua permissions (vì req là string ,entity là obj)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);

}
