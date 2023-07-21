package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static String databaseName = "java_salo";

    public static void main(String[] args) {
        System.out.println("Привіт козаки");
        // створюємо базу даних
        // createDatabase();

        // створюємо таблицю
        // createTable();

        // додаємо дані в таблицю
        //insertDataTable();


                // Приклад використання
                UserDAO userDAO = new UserDAO();

                // Додавання користувача
                User user1 = new User(3,"John Doe", "john.doe@example.com");
                //userDAO.addUser(user1);


             // System.out.println(user1.getName()); // Виведе "John Doe"

                // Оновлення інформації про користувача
              user1.setEmail("john.doe.updated@example.com");
        System.out.println(user1.getId());
              System.out.println(user1.getEmail());
              userDAO.updateUser(user1);

                // Видалення користувача за його id
              // userDAO.deleteUser(1);


    }

    public static void createDatabase() {
        String url = "jdbc:mariadb://localhost:3306";
        String user = "root";
        String password = "";
        String dbName = "";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Вкажіть назву БД:");
            Scanner in = new Scanner(System.in);
            dbName = in.nextLine();

            // Створюємо об'єкт Statement для виконання SQL-запитів
            Statement stmt = con.createStatement();

            // SQL-запит для перевірки існування бази даних
            String checkDbQuery = "SELECT SCHEMA_NAME FROM information_schema.SCHEMATA WHERE SCHEMA_NAME = '" + dbName + "'";

            // Виконуємо запит для перевірки існування бази даних
            ResultSet rs = stmt.executeQuery(checkDbQuery);

            // Перевіряємо, чи є рядки в результаті (база даних існує)
            boolean databaseExists = rs.next();
            rs.close();

            if (databaseExists) {
                System.out.println("База з даним ім'ям уже існує.");
            } else {
                // SQL-запит для створення нової бази даних
                String createDbQuery = "CREATE DATABASE " + dbName;

                // Виконуємо запит для створення бази даних
                stmt.executeUpdate(createDbQuery);
            }

            // Закриваємо об'єкти Statement і Connection
            stmt.close();
        } catch (Exception ex) {
            System.out.println("Проблема з'єднання з базою даних " + ex.getMessage());
        }
    }

    public static void createTable() {
        String url = "jdbc:mariadb://localhost:3306/" + databaseName;
        String user = "root";
        String password = "";
        String dbName = "";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            // Створюємо об'єкт Statement для виконання SQL-запитів
            Statement stmt = con.createStatement();

            // SQL-запит для створення таблиці
            String createTableQuery = "CREATE TABLE users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(50) NOT NULL," +
                    "email VARCHAR(100) NOT NULL" +
                    ")";

            // Виконуємо запит для створення таблиці
            stmt.executeUpdate(createTableQuery);
            System.out.println("Таблиця користувачі створено успішно");
            stmt.close();
        } catch (Exception ex) {
            System.out.println("Проблема з'єднання з базою даних " + ex.getMessage());
        }
    }

    public static void insertDataTable() {
        String url = "jdbc:mariadb://localhost:3306/" + databaseName;
        String user = "root";
        String password = "";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            // Створюємо об'єкт Statement для виконання SQL-запитів
            Statement stmt = con.createStatement();

            // Створюємо підготовлений запит
            PreparedStatement statement = con.prepareStatement("INSERT INTO users (username, email) VALUES (?, ?)");

            // Встановлюємо параметри
            statement.setString(1, "ivan");
            statement.setString(2, "ivan@example.com");

            // Виконуємо запит
            int rowsAffected = statement.executeUpdate();
            System.out.println("Успішно додано дані");
            stmt.close();
        } catch (Exception ex) {
            System.out.println("Проблема з'єднання з базою даних " + ex.getMessage());
        }
    }
}



