package dev.kush.oauth2authserver.repos.client;

import dev.kush.oauth2authserver.models.client.AuthenticationMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthenticationMethodRepository extends JpaRepository<AuthenticationMethod, Integer> {

  @Query(value = """
                           select a from AuthenticationMethod a where a.authenticationMethod= :methodValue
            """)
  Optional<AuthenticationMethod> findByAuthenticationMethod(String methodValue);
}