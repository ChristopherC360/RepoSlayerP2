package com.reposlayers.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "users")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "full_name")
    private String fullName;
    private String email;
    private String username;
    private String password;

}