package com.project.warmyhomes.service.business;

import com.project.warmyhomes.entity.concretes.business.Contact;
import com.project.warmyhomes.exception.ResourceNotFoundException;
import com.project.warmyhomes.payload.mappers.ContactMessageMapper;
import com.project.warmyhomes.payload.messages.ErrorMessages;
import com.project.warmyhomes.payload.messages.SuccessMessages;
import com.project.warmyhomes.payload.request.business.ContactMessageRequest;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.business.ContactMessageResponse;
import com.project.warmyhomes.repository.business.ContactRepository;
import com.project.warmyhomes.service.helper.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactMessageService {

    private final ContactRepository contactRepository;
    private final ContactMessageMapper contactMessageMapper;

    //helpers
    private final PageableHelper pageableHelper;

    /**
     * Create a new contact message.
     * Convert the given ContactMessageRequest to a Contact entity and saves it to the repository.
     *
     * @param contactMessageRequest the request object containing contact message details
     */
    public void createContactMessage(ContactMessageRequest contactMessageRequest) {
        Contact contact = contactMessageMapper.contactMessageRequestToContact(contactMessageRequest);
        contactRepository.save(contact);
    }

    /**
     * Retrieve a paginated list of contact messages based on search query and status.
     * Construct a pageable object with the given sorting parameters and fetches
     * the contact messages from the repository, then maps them to ContactMessageResponse objects.
     *
     * @param query  the search query to filter contact messages by first name, last name, email, or message content
     * @param status the status to filter contact messages
     * @param page   the page number to retrieve
     * @param size   the number of records per page
     * @param sort   the field to sort by
     * @param type   the sort direction (ascending or descending)
     * @return a paginated list of contact message responses
     */
    public Page<ContactMessageResponse> getContactMessagesByPage(String query, Integer status, int page, int size, String sort, String type) {
        // Create pageable object with sorting parameters
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);

        return contactRepository.findContactMessageByQueryAndStatus(query, status, pageable)
                .map(contactMessageMapper::contactToContactMessageResponse);
    }

    /**
     * Retrieve a contact message by its ID, updates its status, and returns the response.
     *
     * @param contactMessageId the ID of the contact message to retrieve
     * @return a ResponseMessage containing the contact message response and relevant information
     * @throws ResourceNotFoundException if the contact message with the specified ID is not found
     */
    public ResponseMessage<ContactMessageResponse> getContactMessageById(Long contactMessageId) {
        // Find the contact message by ID or throw an exception if not found
        Contact contactMessage = contactRepository.findById(contactMessageId).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_CONTACT_MESSAGE, contactMessageId)));

        // Update the status of the contact message
        contactMessage.setStatus(1);
        Contact statusUpdatedContactMessage = contactRepository.save(contactMessage);

        return ResponseMessage.<ContactMessageResponse>builder()
                .message(SuccessMessages.CONTACT_MESSAGE_FOUND)
                .object(contactMessageMapper.contactToContactMessageResponse(statusUpdatedContactMessage))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /**
     * Delete a contact message by its ID.
     *
     * @param contactMessageId the ID of the contact message to delete
     * @throws ResourceNotFoundException if the contact message with the specified ID is not found
     */
    public void deleteContactMessageById(Long contactMessageId) {
        // Find the contact message by ID or throw an exception if not found
        Contact contactMessage = contactRepository.findById(contactMessageId).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_CONTACT_MESSAGE, contactMessageId)));

        contactRepository.deleteById(contactMessage.getId());
    }
}