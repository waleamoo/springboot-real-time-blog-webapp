package com.techqwerty.sprigboot.service.impl;

import java.util.Arrays;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.techqwerty.sprigboot.dto.RegistrationDto;
import com.techqwerty.sprigboot.entity.Role;
import com.techqwerty.sprigboot.entity.User;
import com.techqwerty.sprigboot.repository.RoleRepository;
import com.techqwerty.sprigboot.repository.UserRepository;
import com.techqwerty.sprigboot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void SaveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setName(registrationDto.getFirstName() + " " + registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        // Use spring security to encrypt password
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        
        Role role = roleRepository.findByName("ROLE_GUEST");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
}
