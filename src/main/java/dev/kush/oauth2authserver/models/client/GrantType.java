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
@Table(name = "grant_types")
@NoArgsConstructor
@Getter
@Setter
public class GrantType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "grant_type")
    private String grantType;

    @ManyToMany(mappedBy = "grantTypes")
    private List<Client> clients;

    public GrantType(String grantType, List<Client> clients) {
        this.grantType = grantType;
        this.clients = clients;
    }
}
