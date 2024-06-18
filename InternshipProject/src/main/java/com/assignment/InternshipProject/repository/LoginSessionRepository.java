package com.assignment.InternshipProject.repository;

import com.assignment.InternshipProject.model.LoginSession;
import com.assignment.InternshipProject.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class LoginSessionRepository {
    private final JdbcTemplate jdbcTemplate;
    public LoginSessionRepository(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }
    private final RowMapper<LoginSession> sessionRowMapper = (rs, rowNum) -> {
        LoginSession loginSession = new LoginSession();
        loginSession.setId(rs.getInt("id"));
        loginSession.setLogin_at(rs.getTimestamp("loginat").toLocalDateTime());
        if (rs.getTimestamp("logoutat") != null) {
            loginSession.setLogout_at(rs.getTimestamp("logoutat").toLocalDateTime());
        }
        return loginSession;
    };
    public int save(LoginSession loginSession) throws RuntimeException {
        String sql = "INSERT INTO loginsession (iduser, loginat) VALUES (?, ?)";
        return jdbcTemplate.update(sql,
                loginSession.getUser().getId(),
                loginSession.getLogin_at());
    }
    public Optional<LoginSession> findActiveSessionsByUserId(int userId) throws RuntimeException{
        String sql = "SELECT * FROM loginsession WHERE iduser = ? AND logoutat IS NULL";
        return jdbcTemplate.query(sql, new Object[]{userId}, sessionRowMapper)
                .stream()
                .findFirst();
    }
    public int updateLogoutTime(int sessionId) throws RuntimeException{
        String sql = "UPDATE loginsession SET logoutat = ? WHERE id = ?";
        return jdbcTemplate.update(sql, LocalDateTime.now(), sessionId);
    }


}
