package br.com.casadocodigo.compra;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ItensCarrinhoRequest {

    private @NotNull(message ="Id do livro inválido") Long idLivro;
    private @Min(value =1, message = "Quantidade mínima de livros é 1 ")Integer quantidade;

    public ItensCarrinhoRequest(){
    }

    public ItensCarrinhoRequest(Long idLivro, Integer quantidade) {
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
