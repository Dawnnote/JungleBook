package com.example.junglebook.data.common;


public enum UserRole {

    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    UserRole(String  value) {
        this.value = value;
    }


    private String value;
}
