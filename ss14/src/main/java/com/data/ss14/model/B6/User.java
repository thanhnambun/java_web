package com.data.ss14.model.B6;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class User {
    @NotEmpty(message = "{username.required}")
    private String username;

    @NotEmpty(message = "{password.required}")
    private String password;

    @NotEmpty(message = "{confirmPassword.required}")
    private String confirmPassword;

    @NotEmpty(message = "{email.required}")
    @Email(message = "{email.invalid}")
    private String email;

    public User() {
    }
    public User(String username, String password, String confirmPassword, String email) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}