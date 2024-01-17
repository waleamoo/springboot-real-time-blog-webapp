package com.techqwerty.sprigboot.service;

import com.techqwerty.sprigboot.dto.RegistrationDto;
import com.techqwerty.sprigboot.entity.User;

public interface UserService {
    void SaveUser(RegistrationDto registrationDto);

    User findByEmail(String email);
}
