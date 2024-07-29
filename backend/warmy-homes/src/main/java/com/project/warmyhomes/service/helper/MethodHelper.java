package com.project.warmyhomes.service.helper;

import com.project.warmyhomes.entity.concretes.business.Category;
import com.project.warmyhomes.entity.concretes.user.Role;
import com.project.warmyhomes.entity.concretes.user.User;
import com.project.warmyhomes.exception.BadRequestException;
import com.project.warmyhomes.exception.ResourceNotFoundException;
import com.project.warmyhomes.payload.messages.ErrorMessages;
import com.project.warmyhomes.repository.user.UserRepository;
import com.project.warmyhomes.service.user.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class MethodHelper {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public void isUserBuiltIn(User user) {
        if (Boolean.TRUE.equals(user.getBuiltIn())) {
            throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
        }
    }

    public User isUserExist(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE, userId))
        );
    }

    public User isUserExistByEmail(HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        return userRepository.findByEmail(email);
    }

    public void checkManagerCanUpdateCustomer(User authenticatedUser, User user) {
        Role managerRole = roleService.getUserRole("Manager");
        Role customerRole = roleService.getUserRole("Customer");

        if (authenticatedUser.getRoles().contains(managerRole) && !user.getRoles().contains(customerRole)) {
            throw new BadRequestException(ErrorMessages.BAD_REQUEST_MANAGERS_CAN_ONLY_UPDATE_CUSTOMERS);
        }
    }

    public void isCategoryBuiltIn(Category category) {
        if (Boolean.TRUE.equals(category.getBuiltIn())) {
            throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
        }
    }
}
