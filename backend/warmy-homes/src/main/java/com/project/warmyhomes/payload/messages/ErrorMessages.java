package com.project.warmyhomes.payload.messages;

public class ErrorMessages {

    private ErrorMessages() {
    }

    public static final String PASSWORD_NOT_MATCHED = "Your passwords are not matched";

    public static final String NOT_PERMITTED_METHOD_MESSAGE = "You do not have any permission to do this operation";
    public static final String ROLE_NOT_FOUND = "No such role found, please check the database.";
    public static final String NOT_FOUND_USER_MESSAGE = "Error: User not found with id %s";
    public static final String NOT_FOUND_RESET_PASSWORD_CODE = "Error: User not found with reset password code: %s";

    public static final String ALREADY_REGISTER_PHONE = "Error: User with phone number %s already registered";
    public static final String ALREADY_REGISTER_EMAIL = "Error: User with email address %s already registered";

    public static final String BAD_REQUEST_MANAGERS_CAN_ONLY_UPDATE_CUSTOMERS = "Manager can only update customer";
    public static final String BAD_REQUEST_USER_TO_ADVERT_AND_TOUR_REQUEST = "Error: User cannot be deleted because they have related adverts or tour requests";

    //advert types
    public static final String NOT_FOUND_ADVERT_TYPE_MESSAGE = "Error: Advert type not found with id %s";

    public static final String BAD_REQUEST_ADVERT_TYPE_TO_ADVERT = "Error: Advert type cannot be deleted because they have related adverts";

    //contact message
    public static final String NOT_FOUND_CONTACT_MESSAGE = "Error: Contact Message not found with id %d";

    //country
    public static final String NOT_FOUND_COUNTRY = "Error: Country not found with id %s";

    //city
    public static final String NOT_FOUND_CITY = "Error: City not found with id %s";

    //district
    public static final String NOT_FOUND_DISTRICT = "Error: District not found with id %s";
    //categories
    public static final String NOT_FOUND_CATEGORY_WITH_ID = "Error: Category not found with id %d";
    public static final String NOT_FOUND_CATEGORY_WITH_SLUG = "Error: Category not found with slug %s";

    //category property key
    public static final String NOT_FOUND_CATEGORY_PROPERTY_KEY = "Error: Category property key not found with id %d";

    public static final String BAD_REQUEST_CATEGORY_TO_ADVERT = "Error: Category cannot be deleted because they have related adverts";

    //images
    public static final String NOT_FOUND_IMAGE_MESSAGE = "Error: Image not found with id %s";

    //advert
    public static final String NOT_FOUND_ADVERT_MESSAGE = "Error: Advert not found with id %s";
}
