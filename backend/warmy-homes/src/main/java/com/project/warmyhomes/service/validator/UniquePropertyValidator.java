package com.project.warmyhomes.service.validator;

import com.project.warmyhomes.entity.concretes.user.User;
import com.project.warmyhomes.exception.ConflictException;
import com.project.warmyhomes.payload.messages.ErrorMessages;
import com.project.warmyhomes.payload.request.abstracts.AbstractUserRequest;
import com.project.warmyhomes.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniquePropertyValidator {

    private final UserRepository userRepository;

    public void checkDuplicate(String phone, String email) {
        if (userRepository.existsByPhone(phone)) {
            throw new ConflictException(String.format(ErrorMessages.ALREADY_REGISTER_PHONE, phone));
        }

        if (userRepository.existsByEmail(email)) {
            throw new ConflictException(String.format(ErrorMessages.ALREADY_REGISTER_EMAIL, email));
        }
    }

    public void checkUniqueProperties(User user, AbstractUserRequest abstractUserRequest) {
        String updatedPhone = "";
        String updatedEmail = "";
        boolean isChanged = false;

        //it verifies that we have changed the values
        //phone
        if (!user.getPhone().equalsIgnoreCase(abstractUserRequest.getPhone())) {
            updatedPhone = abstractUserRequest.getPhone();
            isChanged = true;
        }

        //email
        if (!user.getEmail().equalsIgnoreCase(abstractUserRequest.getEmail())) {
            updatedEmail = abstractUserRequest.getEmail();
            isChanged = true;
        }

        if (isChanged) {
            checkDuplicate(updatedPhone, updatedEmail);
        }
    }
}
