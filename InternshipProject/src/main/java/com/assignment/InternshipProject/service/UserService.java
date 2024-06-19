package com.assignment.InternshipProject.service;

import ch.qos.logback.core.joran.util.ParentTag_Tag_Class_Tuple;
import com.assignment.InternshipProject.model.LoginRequest;
import com.assignment.InternshipProject.model.LoginSession;
import com.assignment.InternshipProject.model.User;
import com.assignment.InternshipProject.model.ValidationException;
import com.assignment.InternshipProject.repository.LoginSessionRepository;
import com.assignment.InternshipProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.xml.crypto.Data;
import java.util.Optional;
@Service
public class UserService {
    @Value("${retry.maxAttempts}")
    private int maxAttempts;

    private final UserRepository userRepository;
    private final LoginSessionRepository loginSessionRepository;
    public UserService(UserRepository userRepository, LoginSessionRepository loginSessionRepository){
        this.userRepository = userRepository;
        this.loginSessionRepository = loginSessionRepository;
    }
    private Optional<User> findUserByUserName(String userName) throws RuntimeException{
        int attempts = 0;
        while(attempts < maxAttempts){
            try{
                return userRepository.findUserByUserName(userName);
            }
            catch (RuntimeException e){
                attempts++;
                if(attempts >= maxAttempts){
                    throw new RuntimeException("Failed to find user with userName = " + userName + " after " + attempts + " attempts",e);
                }
                try{
                    Thread.sleep(1000);
                }
                catch (InterruptedException i){
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Retry interrupted", e);
                }
            }
        }
        throw new RuntimeException("Unexpected error");
    }

    public User save(User user) throws ValidationException, RuntimeException {
        Optional<User> existingUser = findUserByUserName(user.getUserName());
        if(existingUser.isPresent()){
            throw new ValidationException("User name is already existed.");
        }
        int attempts = 0;
        while(attempts < maxAttempts){
            try{
                user.validate();
                userRepository.save(user);
                return user;
            }
            catch (RuntimeException e){
                attempts++;
                if(attempts >= maxAttempts){
                    throw new RuntimeException("Failed to save user after " + attempts + " attempts", e);
                }
                try{
                    Thread.sleep(1000);
                }
                catch (InterruptedException i){
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Retry interrupted", e);
                }
            }
        }
        throw new RuntimeException("Unexpected error");
    }
    public Integer login(LoginRequest loginRequest) throws DataAccessException {
        Optional<User> existingUser = findUserByUserName(loginRequest.userName());
        if(existingUser.isPresent()){
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            boolean authenticated = bCryptPasswordEncoder.matches(loginRequest.password(), existingUser.get().getPassword());
            if(authenticated){
                if(!existingUser.get().getLoggedIn()){
                    userRepository.updateState(existingUser.get(), true);
                }
                int sessionId = loginSessionRepository
                        .findActiveSessionsByUserId(existingUser.get().getId())
                        .get()
                        .getId();
                return sessionId;
            }else return null;
        }else{
            throw new RuntimeException("Username doesn't existed");
        }
    }
    public Boolean logout(Integer sessionId) throws DataAccessException{
        Optional<LoginSession> loginSession = loginSessionRepository.findSessionById(sessionId);
        if(loginSession.isPresent()){
            System.out.println(loginSession.get().getIduser());
            System.out.println(loginSession.get().getLogin_at());
            System.out.println(loginSession.get().getLogout_at());
            System.out.println(loginSession.get().getId());


            Optional<User> existingUser = userRepository.findUserById(loginSession.get().getIduser());

            if(existingUser.isPresent()){

                userRepository.updateState(existingUser.get(), false);
                return true;
            }
        }
        return false;
    }
}
