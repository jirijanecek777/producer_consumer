package com.company.repository;

import com.company.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    List<User> findAll() throws SQLException;

    boolean save(Long id, String guid, String name) throws SQLException;

    boolean deleteAll() throws SQLException;
}
