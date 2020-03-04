package com.lrodriguez.campsite.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class User {

    @Id
    private Long id;

    private String email;

    private String fullName;

    public User(String email, String fullName) {
        this.email = email;
        this.fullName = fullName;
    }

}
