package com.project.warmyhomes.payload.mappers;

import com.project.warmyhomes.entity.concretes.business.Contact;
import com.project.warmyhomes.payload.request.business.ContactMessageRequest;
import org.springframework.stereotype.Component;

@Component
public class ContactMessageMapper {

    /**
     *
     * @param contactMessageRequest DTO
     * @return contact from DB
     */
    public Contact contactMessageRequestToContact(ContactMessageRequest contactMessageRequest) {
        return Contact.builder()
                .firstName(contactMessageRequest.getFirstName())
                .lastName(contactMessageRequest.getLastName())
                .email(contactMessageRequest.getEmail())
                .message(contactMessageRequest.getMessage())
                .status(0)
                .build();
    }
}
