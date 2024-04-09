package dev.kush.oauth2authserver.repos.client;

import dev.kush.oauth2authserver.models.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {


    @Query(value = """
                           select c from Client c where c.clientId= :clientId
            """)
    Optional<Client> findByClientId(String clientId);
}