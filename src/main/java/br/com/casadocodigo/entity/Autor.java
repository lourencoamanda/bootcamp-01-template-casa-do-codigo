package br.com.casadocodigo.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Autor  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private @NotBlank(message  ="Nome invalido") String nome;
    private @Email String email;
    private @NotBlank @Size(min = 1 , max =400) String descricao;
    private LocalDateTime datahora = LocalDateTime.now();

    public Autor() {
    }

    public Autor(@NotBlank String nome, @Email String email, @NotBlank @Size(min = 1, max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDateTime getdatahora() { return datahora; }

    public void setdatahora(LocalDateTime datahora) { this.datahora = datahora; }

}
