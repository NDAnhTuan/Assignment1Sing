package com.assignment.InternshipProject.controller;

import com.assignment.InternshipProject.model.*;
import com.assignment.InternshipProject.service.UserService;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<HttpResponse> registor(
            @RequestBody User user
    ){
        try{
            User newUser = userService.save(user);
            return ResponseEntity.created(
                    URI.create("")
            ).body(new HttpResponse(
                    HttpStatus.CREATED.value(),
                    HttpStatus.CREATED,
                    LocalDateTime.now().toString(),
                    "User created",
                    Map.of("user", new UserDto(newUser.getFirstName(), newUser.getLastName(), newUser.getUserName())),
                    ResponseCode.SUCCESS
            ));
        }
        catch (ValidationException e) {
            return ResponseEntity.badRequest().body(new HttpResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST,
                    LocalDateTime.now().toString(),
                    ResponseCode.VALIDATION_ERROR.getMessage(),
                    Map.of("error", e.getMessage()),
                    ResponseCode.VALIDATION_ERROR
            ));
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new HttpResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST,
                    LocalDateTime.now().toString(),
                    ResponseCode.RUNTIME_ERROR.getMessage(),
                    Map.of("error", e.getMessage()),
                    ResponseCode.RUNTIME_ERROR
            ));
        }
    }
    @PostMapping("/login")
    public ResponseEntity<HttpResponse> login(
            @RequestBody LoginRequest loginRequest
    ){
        try{
            Boolean isSuccessLogined = userService.login(loginRequest);
            if(isSuccessLogined){
                return ResponseEntity.ok().body(new HttpResponse(
                        HttpStatus.OK.value(),
                        HttpStatus.OK,
                        LocalDateTime.now().toString(),
                        "Login successfully",
                        Map.of("Success", isSuccessLogined),
                        ResponseCode.SUCCESS
                ));
            }
            else return ResponseEntity.badRequest().body(new HttpResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST,
                    LocalDateTime.now().toString(),
                    ResponseCode.INVALID_CREDENTIALS.getMessage(),
                    Map.of("Success", isSuccessLogined),
                    ResponseCode.INVALID_CREDENTIALS
            ));
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new HttpResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST,
                    LocalDateTime.now().toString(),
                    "Login failed",
                    Map.of("error", e.getMessage()),
                    ResponseCode.RUNTIME_ERROR
            ));
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<HttpResponse> logout(
            @RequestBody User user
    ){
        try{
            Boolean isSuccessLogout= userService.logout(user.getUserName());
            if(isSuccessLogout){
                return ResponseEntity.ok().body(new HttpResponse(
                        HttpStatus.OK.value(),
                        HttpStatus.OK,
                        LocalDateTime.now().toString(),
                        "Logout successfully",
                        Map.of("Success", isSuccessLogout),
                        ResponseCode.SUCCESS
                ));
            }
            else return ResponseEntity.badRequest().body(new HttpResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST,
                    LocalDateTime.now().toString(),
                    ResponseCode.INVALID_CREDENTIALS.getMessage(),
                    Map.of("Success", isSuccessLogout),
                    ResponseCode.INVALID_CREDENTIALS
            ));
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new HttpResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST,
                    LocalDateTime.now().toString(),
                    "Logout failed",
                    Map.of("error", e.getMessage()),
                    ResponseCode.RUNTIME_ERROR
            ));
        }
    }

}
