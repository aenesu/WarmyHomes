package com.project.warmyhomes.repository.user;

import com.project.warmyhomes.entity.concretes.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
