package com.assignment.InternshipProject.repository;

import com.assignment.InternshipProject.model.User;
import org.springframework.cglib.core.Local;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    public UserRepository(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }
    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setFirstName(rs.getString("firstname"));
        user.setLastName(rs.getString("lastname"));
        user.setUserName(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setLoggedIn(rs.getBoolean("islogin"));

        return user;
    };
    public Optional<User> findUserByUserName(String userName) throws RuntimeException {
        String sql = "SELECT * FROM users WHERE username = ?";
//        try {
        return jdbcTemplate.query(sql, new Object[]{userName}, userRowMapper)
                .stream()
                .findFirst();
//        } catch (Exception e) {
//            System.err.println("Error executing query: " + e.getMessage());
//            e.printStackTrace();
//            return Optional.empty();
//        }
    }

    public int save(User user) throws RuntimeException{
//        try{
        String sql = "INSERT INTO users (firstname, lastname, username, password, dateofbirth, createat, lastedited) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                encode(user.getPassword()),
                user.getDateOfBirth(),
                LocalDateTime.now(),
                LocalDateTime.now());
//        }
//        catch (Exception e){
//            System.err.println("Error executing query: " + e.getMessage());
//            return 0;
//        }
    }
    public int updateState(User user, Boolean state) throws RuntimeException{
        String sql = "UPDATE users SET islogin = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                state,
                user.getId());
    }
    public List<User> findAll() throws RuntimeException{
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, userRowMapper);
    }
    public String encode(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }
}
