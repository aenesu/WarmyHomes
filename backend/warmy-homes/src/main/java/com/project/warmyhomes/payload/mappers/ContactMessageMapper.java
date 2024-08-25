package com.project.warmyhomes.payload.mappers;

import com.project.warmyhomes.entity.concretes.business.Contact;
import com.project.warmyhomes.payload.request.business.ContactMessageRequest;
import com.project.warmyhomes.payload.response.business.ContactMessageResponse;
import org.springframework.stereotype.Component;

@Component
public class ContactMessageMapper {

    /**
     * @param contactMessageRequest DTO
     * @return contact from DB
     */
    public Contact mapContactMessageRequestToContact(ContactMessageRequest contactMessageRequest) {
        return Contact.builder()
                .firstName(contactMessageRequest.getFirstName())
                .lastName(contactMessageRequest.getLastName())
                .email(contactMessageRequest.getEmail())
                .message(contactMessageRequest.getMessage())
                .status(0)
                .build();
    }

    /**
     * @param contact object
     * @return ContactMessageResponse DTO object
     */
    public ContactMessageResponse mapContactToContactMessageResponse(Contact contact) {
        return ContactMessageResponse.builder()
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .message(contact.getMessage())
                .build();
    }
}
