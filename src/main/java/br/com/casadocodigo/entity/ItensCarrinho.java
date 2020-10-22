package br.com.casadocodigo.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class ItensCarrinho implements Serializable {
    private static final long serialVersionUID = 1L;

    private @NotNull(message ="Id do livro inválido") Long idLivro;
    private @Min(value =1, message = "Quantidade mínima de livros é 1 ")Integer quantidade;

    public ItensCarrinho(){
    }

    public ItensCarrinho(@NotNull(message = "Id do livro inválido") Long idLivro, @Min(value = 1, message = "Quantidade mínima de livros é 1 ") Integer quantidade) {
        this.idLivro = idLivro;
        this.quantidade = quantidade;
    }

    public Long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Long idLivro) {
        this.idLivro = idLivro;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}
