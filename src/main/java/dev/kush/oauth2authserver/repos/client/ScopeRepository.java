package dev.kush.oauth2authserver.repos.client;

import dev.kush.oauth2authserver.models.client.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ScopeRepository extends JpaRepository<Scope, Integer> {

    @Query(value = """
                            select s from Scope s where s.scope= :scopeValue
             """)
    Optional<Scope> findByScope(String scopeValue);
}