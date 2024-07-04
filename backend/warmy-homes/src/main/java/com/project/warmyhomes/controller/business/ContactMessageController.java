package com.project.warmyhomes.controller.business;

import com.project.warmyhomes.payload.request.business.ContactMessageRequest;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.business.ContactMessageResponse;
import com.project.warmyhomes.service.business.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contact-messages")
@RequiredArgsConstructor
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    @PostMapping // http://localhost:8080/contact-messages + POST + JSON
    public void createContactMessage(@Valid @RequestBody ContactMessageRequest contactMessageRequest) {
        contactMessageService.createContactMessage(contactMessageRequest);
    }

    @GetMapping // http://localhost:8080/contact-messages?q=deneme&status=0&page=0&size=10&sort=createDate&type=asc + GET
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public Page<ContactMessageResponse> getContactMessagesByPage(
            @RequestParam(value = "q") String query,
            @RequestParam(value = "status") Integer status,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sort", defaultValue = "create_date") String sort,
            @RequestParam(value = "type", defaultValue = "asc") String type
    ) {
        return contactMessageService.getContactMessagesByPage(query, status, page, size, sort, type);
    }

    @GetMapping("/{contactMessageId}") // http://localhost:8080/contact-messages/:contactMessageId + GET
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public ResponseMessage<ContactMessageResponse> getContactMessageById(@PathVariable Long contactMessageId) {
        return contactMessageService.getContactMessageById(contactMessageId);
    }

    @DeleteMapping("/{contactMessageId}") // http://localhost:8080/contact-messages/:contactMessageId + DELETE
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public void deleteContactMessageById(@PathVariable Long contactMessageId) {
        contactMessageService.deleteContactMessageById(contactMessageId);
    }
}
