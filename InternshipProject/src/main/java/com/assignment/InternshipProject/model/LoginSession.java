package com.assignment.InternshipProject.model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class LoginSession {
    private Integer id;
    private LocalDateTime login_at;
    private LocalDateTime logout_at;
    private User user;

    public LoginSession() {
    }

    public LoginSession(User user) {
        this.user = user;
        this.login_at = LocalDateTime.now();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getLogin_at() {
        return login_at;
    }

    public void setLogin_at(LocalDateTime login_at) {
        this.login_at = login_at;
    }

    public LocalDateTime getLogout_at() {
        return logout_at;
    }

    public void setLogout_at(LocalDateTime logout_at) {
        this.logout_at = logout_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
