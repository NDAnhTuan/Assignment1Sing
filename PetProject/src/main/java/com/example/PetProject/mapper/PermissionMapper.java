package com.example.PetProject.mapper;

import com.example.PetProject.dto.request.MyUserCreationRequest;
import com.example.PetProject.dto.request.MyUserUpdateRequest;
import com.example.PetProject.dto.request.PermissionRequest;
import com.example.PetProject.dto.response.PermissionResponse;
import com.example.PetProject.dto.response.MyUserResponse;
import com.example.PetProject.entity.MyUser;
import com.example.PetProject.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
//import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
//    MyUserMapper INSTANCE = Mappers.getMapper(MyUserMapper.class);
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);

}
