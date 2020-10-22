package br.com.casadocodigo.pais;

import javax.validation.constraints.NotBlank;

public class PaisRequest {

    private @NotBlank (message = "{ Descrição Inválida }") String descricao;

    public PaisRequest() {
    }

    public PaisRequest(@NotBlank(message = "{ Descrição Inválida }") String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
