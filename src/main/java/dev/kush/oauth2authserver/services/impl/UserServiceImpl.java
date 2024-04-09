package dev.kush.oauth2authserver.services.impl;

import dev.kush.oauth2authserver.dtos.SignupDto;
import dev.kush.oauth2authserver.models.user.Role;
import dev.kush.oauth2authserver.models.user.User;
import dev.kush.oauth2authserver.repos.user.RoleRepository;
import dev.kush.oauth2authserver.repos.user.UserRepository;
import dev.kush.oauth2authserver.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
            () -> new UsernameNotFoundException("User not found")
        );
    }

    @Override
    public User saveUser(SignupDto signupDto) {
        Role role = findOrCreateRole("USER");
        User user = new User(signupDto.username(), signupDto.password(), List.of(role));
        role.setUsers(List.of(user));
        return userRepository.save(user);

    }

    private Role findOrCreateRole(String roleName) {
        return roleRepository.findRoleByRoleName(roleName)
                .orElseGet(() -> roleRepository.save(new Role("USER")));
    }

}
