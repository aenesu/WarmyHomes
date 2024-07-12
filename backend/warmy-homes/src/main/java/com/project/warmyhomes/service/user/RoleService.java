package com.project.warmyhomes.service.user;

import com.project.warmyhomes.entity.concretes.user.Role;
import com.project.warmyhomes.exception.ResourceNotFoundException;
import com.project.warmyhomes.payload.messages.ErrorMessages;
import com.project.warmyhomes.repository.user.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    /**
     * Retrieve all user roles from the repository.
     *
     * @return a list of all user roles
     */
    public List<Role> getAllUserRole() {
        return roleRepository.findAll();
    }

    /**
     * Retrieve a user role by its name.
     *
     * @param role the name of the role to retrieve
     * @return the Role object corresponding to the given role name
     * @throws ResourceNotFoundException if the role is not found
     */
    public Role getUserRole(String role) {
        return roleRepository.findByRoleName(role)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ROLE_NOT_FOUND));
    }
}
