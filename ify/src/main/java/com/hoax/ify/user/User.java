package com.hoax.ify.user;

import lombok.Data;

@Data
public class User {

    private String username;
    private String displayName;
    private String password;

    public User(String username, String displayName, String password) {
        this.username = username;
        this.displayName = displayName;
        this.password = password;
    }
}
