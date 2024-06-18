package com.project.warmyhomes.service.helper;

import com.project.warmyhomes.entity.concretes.user.User;
import com.project.warmyhomes.exception.BadRequestException;
import com.project.warmyhomes.payload.messages.ErrorMessages;
import com.project.warmyhomes.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MethodHelper {

    private final UserRepository userRepository;

    public void isUserBuiltIn(User user) {
        if (Boolean.TRUE.equals(user.getBuiltIn())) {
            throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
        }
    }
}
