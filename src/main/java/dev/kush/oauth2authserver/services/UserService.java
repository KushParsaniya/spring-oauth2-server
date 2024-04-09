package dev.kush.oauth2authserver.services;

import dev.kush.oauth2authserver.dtos.SignupDto;
import dev.kush.oauth2authserver.models.user.User;

public interface UserService {
    User findUserByUsername(String username);

    User saveUser(SignupDto signupDto);
}
