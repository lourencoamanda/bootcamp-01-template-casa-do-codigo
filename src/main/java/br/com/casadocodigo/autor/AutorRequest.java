package br.com.casadocodigo.autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AutorRequest {

    private @NotBlank(message  ="{ Nome Inválido }") String nome;
    private @Email(message  ="{ Email Inválido }") String email;
    private @NotBlank(message = "{ Descrição Inválida }") @Size(min = 1 , max =400) String descricao;

    public AutorRequest() {
    }

    public AutorRequest(@NotBlank(message = "{ Nome Inválido }") String nome, @Email(message = "{ Email Inválido }") String email, @NotBlank(message = "{ Descrição Inválida }") @Size(min = 1, max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
