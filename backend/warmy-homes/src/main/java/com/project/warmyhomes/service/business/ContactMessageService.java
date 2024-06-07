package com.project.warmyhomes.service.business;

import com.project.warmyhomes.entity.concretes.business.Contact;
import com.project.warmyhomes.payload.mappers.ContactMessageMapper;
import com.project.warmyhomes.payload.request.business.ContactMessageRequest;
import com.project.warmyhomes.repository.business.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactMessageService {

    private final ContactRepository contactRepository;
    private final ContactMessageMapper contactMessageMapper;

    public ResponseEntity<String> createContactMessage(ContactMessageRequest contactMessageRequest) {
        Contact contact = contactMessageMapper.contactMessageRequestToContact(contactMessageRequest);
        contactRepository.save(contact);
        return ResponseEntity.ok("");
    }
}
