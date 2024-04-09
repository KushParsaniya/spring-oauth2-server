package dev.kush.oauth2authserver.models.client;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "redirect_urls")
public class RedirectUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public RedirectUrl(String url, Client client) {
        this.url = url;
        this.client = client;
    }
}