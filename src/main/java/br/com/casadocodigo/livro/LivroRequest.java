package br.com.casadocodigo.livro;

import br.com.casadocodigo.entity.Autor;
import br.com.casadocodigo.entity.Categoria;
import br.com.casadocodigo.entity.Livro;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroRequest {

    private @NotBlank (message  ="{ Titulo Inválido }")  String titulo;
    private @NotBlank (message  ="{ Resumo Inválido }") @Size(max = 500) String resumo;
    private @NotBlank (message  ="{ Sumário Inválido }") String sumario;
    private @NotNull (message  ="{ Preço Inválido }") @Min(20) BigDecimal preco;
    private @Min(100) Integer numPag;
    private @NotBlank (message  ="{ ISBN Inválido }")String isbn;
    private @Future(message  ="{ Data de Publicação não está no futuro }") LocalDate dataPublicacao;

    @NotNull
    private Long idCategoria;

    @NotNull
    private Long idAutor;

    public LivroRequest() {
    }

    public LivroRequest(@NotBlank(message = "{ Titulo Inválido }") String titulo, @NotBlank(message = "{ Resumo Inválido }") @Size(max = 500) String resumo, @NotBlank(message = "{ Sumário Inválido }") String sumario, @NotNull(message = "{ Preço Inválido }") @Min(20) BigDecimal preco, @Min(100) Integer numPag, @NotBlank(message = "{ ISBN Inválido }") String isbn, @Future(message = "{ Data de Publicação não está no futuro }") LocalDate dataPublicacao, @NotNull Long idCategoria, @NotNull Long idAutor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numPag = numPag;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.idCategoria = idCategoria;
        this.idAutor = idAutor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() { return resumo; }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public void setSumario(String sumario) {
        this.sumario = sumario;
    }

    public Integer getNumPag() {
        return numPag;
    }

    public void setNumPag(Integer numPag) {
        this.numPag = numPag;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getIdCategoria() { return idCategoria; }

    public void setIdCategoria(Long idCategoria) { this.idCategoria = idCategoria; }

    public Long getIdAutor() { return idAutor; }

    public void setIdAutor(Long idAutor) { this.idAutor = idAutor; }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    @Override
    public String toString() {
        return "Categoria: [ Titulo=" + titulo + ", " + "resumo=" + resumo + "" + ",sumario=" + sumario + ", " +
                ",Preço=" + preco + ", " + ",Numero Paginas=" + numPag + ",ISBN=" + isbn + ", " + ",Categoria=" + idCategoria +
                ", Autor=" + idAutor +   ", " + " Data publicação= " + dataPublicacao + " ]";
    }

    public Livro toModel(EntityManager manager) {
        @NotNull Autor autor = manager.find(Autor.class, idAutor);
        @NotNull Categoria categoria = manager.find(Categoria.class, idCategoria);

        Assert.state(autor!=null,"Você esta querendo cadastrar um livro para um autor que nao existe no banco "+idAutor);
        Assert.state(categoria!=null,"Você esta querendo cadastrar um livro para uma categoria que nao existe no banco "+idCategoria);

        return new Livro(titulo, resumo, sumario, preco, numPag, isbn, dataPublicacao, autor, categoria);
    }

}
