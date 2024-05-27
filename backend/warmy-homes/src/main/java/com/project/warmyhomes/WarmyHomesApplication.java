package com.project.warmyhomes;

import com.project.warmyhomes.entity.concretes.user.Role;
import com.project.warmyhomes.entity.concretes.user.User;
import com.project.warmyhomes.repository.user.RoleRepository;
import com.project.warmyhomes.repository.user.UserRepository;
import com.project.warmyhomes.service.user.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

//import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class WarmyHomesApplication implements CommandLineRunner {

    private final RoleService roleService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    //private final PasswordEncoder passwordEncoder; -> When security is added, password encoder will be used for the password.

    public WarmyHomesApplication(RoleService roleService, RoleRepository roleRepository, UserRepository userRepository) {
        this.roleService = roleService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(WarmyHomesApplication.class, args);
    }

    // The "run" method runs when the application runs.
    @Override
    public void run(String... args) throws Exception {
        //Add roles
        if (roleService.getAllUserRole().isEmpty()) {
            Role admin = new Role();
            admin.setRoleName("Admin");
            roleRepository.save(admin);

            Role manager = new Role();
            manager.setRoleName("Manager");
            roleRepository.save(manager);

            Role customer = new Role();
            customer.setRoleName("Customer");
            roleRepository.save(customer);
        }

        // Add user as super administrator.
        if (!userRepository.existsByEmail("super.admin@gmail.com")) {
            User superAdmin = new User();
            superAdmin.setFirstName("Warmy");
            superAdmin.setLastName("Homes");
            superAdmin.setEmail("super.admin@gmail.com");
            superAdmin.setPhone("111-111-1111");
            superAdmin.setPasswordHash("Superadmin1234"); //passwordEncoder.encode("Superadmin1234")
            superAdmin.setBuiltIn(true);
            superAdmin.setCreateAt(LocalDateTime.now());
            superAdmin.setRoles(Collections.singletonList(roleRepository.findByRoleName("Admin")));
            userRepository.save(superAdmin);
        }
    }
}
