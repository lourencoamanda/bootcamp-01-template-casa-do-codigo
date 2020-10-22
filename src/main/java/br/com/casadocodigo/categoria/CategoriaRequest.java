package br.com.casadocodigo.categoria;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    private @NotBlank (message = "{ Descrição Inválida }") String descricao;

    public CategoriaRequest() {
    }

    public CategoriaRequest(@NotBlank(message = "{ Descrição Inválida }") String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
