package com.project.warmyhomes.payload.messages;

public class ErrorMessages {

    private ErrorMessages() {
    }

    public static final String ROLE_NOT_FOUND = "No such role found, please check the database.";

    public static final String ALREADY_REGISTER_PHONE = "Error: User with phone number %s already registered";
    public static final String ALREADY_REGISTER_EMAIL = "Error: User with email address %s already registered";
}
