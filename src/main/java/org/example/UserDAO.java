package org.example;

import java.sql.*;

public class UserDAO {
    private final String databaseName = "java_salo";

    private final String url = "jdbc:mariadb://localhost:3306/" + databaseName;

    private final String userr = "root";
    private final String password = "";

    public void addUser(User user) {
        String query = "INSERT INTO users (username, email) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(url, userr, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, userr, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("username");
                    String email = resultSet.getString("email");
                    return new User(id, name, email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUser(User user) {

        String query = "UPDATE users SET  username = ?, email = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, userr, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, userr, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
