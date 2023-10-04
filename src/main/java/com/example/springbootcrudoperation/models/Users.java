package com.example.springbootcrudoperation.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Users {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Basic
    @Column(name = "username", nullable = false, length = 150)
    private String username;


    @Basic
    @Column(name = "email", nullable = false, length = 250)
    private String email;


    public Users(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
