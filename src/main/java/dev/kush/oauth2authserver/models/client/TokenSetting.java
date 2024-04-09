package dev.kush.oauth2authserver.models.client;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "token_settings")
@NoArgsConstructor
@Getter
@Setter
public class TokenSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "access_token_ttl")
    private Integer accessTokenTtl;

    @ManyToOne
    @JoinColumn(name = "token_type")
    private TokenType tokenType;

    @OneToOne
    private Client client;

    public TokenSetting(Integer accessTokenTtl, TokenType tokenType, Client client) {
        this.accessTokenTtl = accessTokenTtl;
        this.tokenType = tokenType;
        this.client = client;
    }
}
