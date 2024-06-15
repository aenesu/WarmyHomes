package com.project.warmyhomes.service.helper;

import com.project.warmyhomes.entity.concretes.user.User;
import com.project.warmyhomes.exception.ResourceNotFoundException;
import com.project.warmyhomes.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MethodHelper {

    private final UserRepository userRepository;

}
