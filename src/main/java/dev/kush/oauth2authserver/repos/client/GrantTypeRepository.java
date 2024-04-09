package dev.kush.oauth2authserver.repos.client;

import dev.kush.oauth2authserver.models.client.GrantType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GrantTypeRepository extends JpaRepository<GrantType, Integer> {

  @Query(value = """
                           select g from GrantType g where g.grantType= :grantTypeValue
            """)
  Optional<GrantType> findByGrantType(String grantTypeValue);
}