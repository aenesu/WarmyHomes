package com.project.warmyhomes.service.helper;

import com.project.warmyhomes.entity.concretes.business.*;
import com.project.warmyhomes.entity.concretes.user.Role;
import com.project.warmyhomes.entity.concretes.user.User;
import com.project.warmyhomes.exception.BadRequestException;
import com.project.warmyhomes.exception.ResourceNotFoundException;
import com.project.warmyhomes.payload.messages.ErrorMessages;
import com.project.warmyhomes.repository.business.*;
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

    private final CategoryRepository categoryRepository;
    private final CategoryPropertyKeyRepository categoryPropertyKeyRepository;

    private final AdvertTypeRepository advertTypeRepository;
    private final AdvertRepository advertRepository;

    private final ImageRepository imageRepository;

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

    public AdvertType isAdvertTypeExistById(Long advertTypeId) {
        return advertTypeRepository.findById(advertTypeId).
                orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_ADVERT_TYPE_MESSAGE, advertTypeId)));
    }

    public void isCategoryBuiltIn(Category category) {
        if (Boolean.TRUE.equals(category.getBuiltIn())) {
            throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
        }
    }

    public Category isCategoryExistById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_CATEGORY_WITH_ID, categoryId)));

    }

    public void isCategoryPropertyKeyBuiltIn(CategoryPropertyKey categoryPropertyKey) {
        if (Boolean.TRUE.equals(categoryPropertyKey.getBuiltIn())) {
            throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
        }
    }

    public CategoryPropertyKey isCategoryPropertyKeyExistById(Long propertyKeyId) {
        return categoryPropertyKeyRepository.findById(propertyKeyId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_CATEGORY_PROPERTY_KEY, propertyKeyId)));
    }

    public Advert isAdvertExistById(Long advertId) {
        return advertRepository.findById(advertId).
                orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_ADVERT_MESSAGE, advertId)));
    }

    public Image isImageExistById(Long imageId){
        return imageRepository.findById(imageId).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_IMAGE_MESSAGE, imageId))
        );
    }
}
