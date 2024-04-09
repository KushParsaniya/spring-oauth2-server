package dev.kush.oauth2authserver.models.client;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String clientId;

    private String clientSecret;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "clients_grant_types",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "grant_type_id")
    )
    private List<GrantType> grantTypes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "clients_scopes",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "scope_id")
    )
    private List<Scope> scopes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "clients_authentication_methods",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "authentication_method_id")
    )
    private List<AuthenticationMethod> authenticationMethods;

    @OneToOne(mappedBy = "client")
    private TokenSetting tokenSetting;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<RedirectUrl> redirectUrls;


}
