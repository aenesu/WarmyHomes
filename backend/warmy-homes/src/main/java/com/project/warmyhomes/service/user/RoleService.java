package com.project.warmyhomes.service.user;

import com.project.warmyhomes.entity.concretes.user.Role;
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
}
