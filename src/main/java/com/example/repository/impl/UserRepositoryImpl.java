package com.example.repository.impl;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.exception.UserRetrievedFailedException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final DataSource dataSource;

    public UserRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void populateUsers() throws SQLException {
        var connection = dataSource.getConnection();

        var statement = connection.createStatement();

        statement.executeUpdate("create table if not exists User(name varchar(50), email varchar(150))");
        statement.executeUpdate("insert into User(name, email) values('john', 'john@gmail.com')");
        statement.executeUpdate("insert into User(name, email) values('max', 'max@gmail.com')");

        connection.close();
    }

    @Override
    public List<User> getAllUsers() {
        try {
            var connection = dataSource.getConnection();
            var statement = connection.createStatement();

            var resultSet =
                    statement.executeQuery("select name as user_name, email as user_email from User");

            var users = fetchUsersFromResultSet(resultSet);

            connection.close();

            return users;
        }
        catch (SQLException e) {
            throw new UserRetrievedFailedException();
        }
    }

    private List<User> fetchUsersFromResultSet(ResultSet resultSet) throws SQLException {
        final List<User> users = new ArrayList<>();

        while (resultSet.next()) {
            var name = resultSet.getString("user_name");
            var email = resultSet.getString("user_email");

            var user = new User(name, email);

            users.add(user);
        }

        return users;
    }

}
