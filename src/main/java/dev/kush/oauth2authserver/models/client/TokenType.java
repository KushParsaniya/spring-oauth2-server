package dev.kush.oauth2authserver.models.client;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "token_types")
@Getter
@Setter
@NoArgsConstructor
public class TokenType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tokenType;

    @OneToMany(mappedBy = "tokenType")
    private List<TokenSetting> tokenSettings;

    public TokenType(String tokenType, List<TokenSetting> tokenSettings) {
        this.tokenType = tokenType;
        this.tokenSettings = tokenSettings;
    }

    public TokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
