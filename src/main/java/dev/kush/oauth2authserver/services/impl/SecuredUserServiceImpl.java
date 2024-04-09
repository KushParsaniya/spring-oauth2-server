package dev.kush.oauth2authserver.services.impl;

import dev.kush.oauth2authserver.models.user.SecuredUser;
import dev.kush.oauth2authserver.services.SecuredUserService;
import dev.kush.oauth2authserver.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecuredUserServiceImpl implements SecuredUserService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new SecuredUser(userService.findUserByUsername(username));
    }
}
