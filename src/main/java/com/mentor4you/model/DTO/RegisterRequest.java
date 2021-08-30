package com.mentor4you.model.DTO;

public class RegisterRequest {

    private final boolean roles_id;

    private final String email;
    private final String password;
    private final String first_name;
    private final String last_name;

    private final String phone_number;

    public RegisterRequest(boolean roles_id, String email, String password, String first_name, String last_name, String phone_number) {
        this.roles_id = roles_id;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
    }

    public boolean getRoles_id() {
        return roles_id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
