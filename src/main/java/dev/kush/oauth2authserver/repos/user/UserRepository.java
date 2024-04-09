package dev.kush.oauth2authserver.repos.user;

import dev.kush.oauth2authserver.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = """
                            select u from User u where u.username= :username
             """)
    Optional<User> findByUsername(String username);
}