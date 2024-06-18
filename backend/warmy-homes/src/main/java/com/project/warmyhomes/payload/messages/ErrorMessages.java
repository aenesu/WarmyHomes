package com.project.warmyhomes.payload.messages;

public class ErrorMessages {

    private ErrorMessages() {
    }

    public static final String PASSWORD_NOT_MATCHED = "Your passwords are not matched";

    public static final String NOT_PERMITTED_METHOD_MESSAGE = "You do not have any permission to do this operation";
    public static final String ROLE_NOT_FOUND = "No such role found, please check the database.";
    public static final String NOT_FOUND_RESET_PASSWORD_CODE = "Error: User not found with reset password code: %s";
    public static final String ALREADY_REGISTER_PHONE = "Error: User with phone number %s already registered";
    public static final String ALREADY_REGISTER_EMAIL = "Error: User with email address %s already registered";
}
