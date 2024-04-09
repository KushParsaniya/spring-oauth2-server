package dev.kush.oauth2authserver.models.client;

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
@Table(name = "scopes")
@NoArgsConstructor
@Getter
@Setter
public class Scope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String scope;

    @ManyToMany(mappedBy = "scopes")
    private List<Client> clients;

    public Scope(String scope, List<Client> clients) {
        this.scope = scope;
        this.clients = clients;
    }
}
