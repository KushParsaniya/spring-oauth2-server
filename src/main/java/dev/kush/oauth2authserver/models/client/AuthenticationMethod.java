package dev.kush.oauth2authserver.models.client;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "authentication_methods")
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "authentication_method")
    private String authenticationMethod;

    @ManyToMany
    private List<Client> clients;

    public AuthenticationMethod(String authenticationMethod, List<Client> clients) {
        this.authenticationMethod = authenticationMethod;
        this.clients = clients;
    }
}
