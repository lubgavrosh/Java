package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "users") // Specify the table name for the entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname", length =100, nullable = false)
    private String firstname;
    @Column(name = "lastname", length =100, nullable = false)
    private String lastname;
    @Column(name = "phone", length =20, nullable = false)
    private String phone;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    // Default constructor
    public User() {
    }

    // Constructor with all properties
    public User(String firstname,String lastname,String phone, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
}
