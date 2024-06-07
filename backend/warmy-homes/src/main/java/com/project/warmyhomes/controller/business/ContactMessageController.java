package com.project.warmyhomes.controller.business;

import com.project.warmyhomes.payload.request.business.ContactMessageRequest;
import com.project.warmyhomes.service.business.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/contact-messages")
@RequiredArgsConstructor
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    @PostMapping // http://localhost:8080/contact-messages + POST + JSON
    public ResponseEntity<String> createContactMessage(@Valid @RequestBody ContactMessageRequest contactMessageRequest){
        return contactMessageService.createContactMessage(contactMessageRequest);
    }
}
