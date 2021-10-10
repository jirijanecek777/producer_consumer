package com.company.repository;

import com.company.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbUserRepository implements UserRepository {

    private static final String SQL_SELECT = "SELECT USER_ID, USER_GUID, USER_NAME FROM SUSERS";
    private static final String SQL_INSERT = "INSERT INTO SUSERS (USER_ID, USER_GUID, USER_NAME) VALUES (?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM SUSERS";

    @Override
    public List<User> findAll() throws SQLException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT);

            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(
                        User.builder()
                                .withId(rs.getLong("USER_ID"))
                                .withGuid(rs.getString("USER_GUID"))
                                .withName(rs.getString("USER_NAME"))
                                .build()
                );
            }

            return users;
        }
    }

    @Override
    public boolean save(final Long id, final String guid, final String name) throws SQLException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setLong(1, id);
            statement.setString(2, guid);
            statement.setString(3, name);

            return statement.execute();
        }
    }

    @Override
    public boolean deleteAll() throws SQLException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            Statement statement = connection.createStatement();
            return statement.execute(SQL_DELETE);
        }
    }
}
