package org.example.model;

import jakarta.persistence.*;
import lombok.*;



@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int ID;
    @Column(name = "name",unique = true)
    private String username;
    @Column(name = "password",unique = true)
    private String password;
    @Column(name = "email")
    private String email;
    @Transient
    private String confirmPassword;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;

    }



    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }

}
