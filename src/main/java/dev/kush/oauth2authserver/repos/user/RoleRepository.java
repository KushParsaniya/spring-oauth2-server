package dev.kush.oauth2authserver.repos.user;

import dev.kush.oauth2authserver.models.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(value = """
                            select r from Role r where r.role= :roleName
             """)
    Optional<Role> findRoleByRoleName(String roleName);
}