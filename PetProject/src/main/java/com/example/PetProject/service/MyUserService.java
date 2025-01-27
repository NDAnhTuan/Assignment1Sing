package com.example.PetProject.service;

import com.example.PetProject.dto.request.MyUserCreationRequest;
import com.example.PetProject.dto.request.MyUserUpdateRequest;
import com.example.PetProject.dto.response.MyUserResponse;
import com.example.PetProject.entity.MyUser;
import com.example.PetProject.enums.Role;
import com.example.PetProject.exception.AppException;
import com.example.PetProject.exception.ErrorCode;
import com.example.PetProject.mapper.MyUserMapper;
import com.example.PetProject.repository.MyUserRepository;
import com.example.PetProject.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j

public class MyUserService {
    MyUserRepository myUserRepository;
    RoleRepository roleRepository;
    MyUserMapper myUserMapper;
    PasswordEncoder passwordEncoder;


    public MyUserResponse createMyUser(MyUserCreationRequest request) {
        MyUser myUser = myUserMapper.toMyUser(request);
        myUser.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        //myUser.setRoles(roles);

        try {
            myUser = myUserRepository.save(myUser);
        }
        catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        return myUserMapper.toMyUserResponse(myUser);
    }

    public MyUserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        MyUser myUser = myUserRepository.findByEmail(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED)
        );

        return myUserMapper.toMyUserResponse(myUser);

    }
    public MyUserResponse updateMyUser(String userId,MyUserUpdateRequest request) {
        MyUser myUser = myUserRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        myUserMapper.updateMyUser(myUser, request);
        myUser.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        myUser.setRoles(new HashSet<>(roles));

        return myUserMapper.toMyUserResponse(myUserRepository.save(myUser));
    }
    public void deleteMyUser(String userId) {
        myUserRepository.deleteById(userId);
    }
    //Protect Method before access
    // hasRole = Role_?
//    @PreAuthorize("hasRole('ADMIN')")
    // hasAuthority = full Scope name
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<MyUserResponse> getMyUsers() {
        return myUserRepository.findAll().stream().map(myUserMapper::toMyUserResponse).toList();
    }
    @PostAuthorize("returnObject.username == authentication.name")
    // Cho thuc thi nhung khong tra ve ket qua (return)
    public MyUserResponse getMyUser(String id) {
        return myUserMapper.toMyUserResponse(
                myUserRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED))
        );
    }
}
