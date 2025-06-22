package com.Xsupport.Entity;

public enum Role {

    USER,
    CUSTOMER,
    ADMIN;


    public static boolean isValid(String role) {
        try {
            Role.valueOf(role.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}