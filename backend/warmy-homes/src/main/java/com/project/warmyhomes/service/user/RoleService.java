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


    public List<Role> getAllUserRole() {
        return roleRepository.findAll();
    }

    public Role getUserRole(String role) {
        return roleRepository.findByRoleName(role)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ROLE_NOT_FOUND));
    }
}
