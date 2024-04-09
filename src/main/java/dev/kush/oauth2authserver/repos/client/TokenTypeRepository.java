package dev.kush.oauth2authserver.repos.client;

import dev.kush.oauth2authserver.models.client.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TokenTypeRepository extends JpaRepository<TokenType, Integer> {

    @Query(value = """
                           select t from TokenType t where t.tokenType= :tokenType
            """)
    Optional<TokenType> findByTokenType(String tokenType);
}