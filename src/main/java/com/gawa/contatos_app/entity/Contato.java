package com.gawa.contatos_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "contato" , schema = "desafio")
public class Contato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contato_id")
    private Long id;

    @Column(name = "contato_nome" , nullable = false, length = 200)
    private String contato_nome;

    @Column(name = "contato_email", nullable = false, unique = true)
    private String contato_email;

    @Column(name = "contato_celular", nullable = false, unique = true)
    private String contato_celular;

    @Column(name = "contato_telefone")
    private String contato_telefone;

    @Enumerated(EnumType.STRING)
    @Column(name = "contato_sn_favorito")
    private Role contato_sn_favorito;

    @Enumerated(EnumType.STRING)
    @Column(name = "contato_sn_ativo")
    private Role contato_sn_ativo;

    @Column(name = "contato_dh_cad")
    private LocalDateTime contato_dh_cad;

    @PrePersist
    protected void onCreate() {
        this.contato_dh_cad = LocalDateTime.now().withSecond(0).withNano(0);
    }

    @PreUpdate
    protected void onUpdate() {
        this.contato_dh_cad = LocalDateTime.now().withSecond(0).withNano(0);
    }

    public enum Role {
        S,N
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return Objects.equals(id, contato.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Contato{" +
                "id=" + id +
                '}';
    }
}
