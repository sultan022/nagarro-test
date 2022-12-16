package com.nagarro.test.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "user")
@Data
public class UserEntity {


    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "is_logged_in")
    private Boolean isLoggedIn;

    @Column(name="role")
    private String role;



}
