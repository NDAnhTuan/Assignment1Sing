package com.example.PetProject.mapper;

import com.example.PetProject.dto.request.MyUserCreationRequest;
import com.example.PetProject.dto.request.MyUserUpdateRequest;
import com.example.PetProject.dto.response.MyUserResponse;
import com.example.PetProject.entity.MyUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
//import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MyUserMapper {
//    MyUserMapper INSTANCE = Mappers.getMapper(MyUserMapper.class);
    MyUser toMyUser(MyUserCreationRequest request);
    MyUserResponse toMyUserResponse(MyUser myUser);

    @Mapping(target = "roles", ignore = true)
    void updateMyUser(@MappingTarget MyUser myUser, MyUserUpdateRequest request);

}
