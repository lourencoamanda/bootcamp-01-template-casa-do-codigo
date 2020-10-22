package br.com.casadocodigo.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Livro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private @NotBlank @NotEmpty String titulo;
    private @NotBlank @NotEmpty @Size(min = 1 , max =500) String resumo;
    private @NotBlank @NotEmpty String sumario;
    private @Min(20) BigDecimal preco = new BigDecimal(0);
    private @Min(100)  Integer numPag;
    private @NotBlank String isbn;
    private @Future LocalDate dataPublicacao;

    @ManyToOne
    private @NotNull @Valid Autor autor;

    @ManyToOne
    private @NotNull @Valid Categoria categoria;

    @Deprecated
    public Livro() {
    }

    public Livro(@NotBlank @NotEmpty String titulo, @NotBlank @NotEmpty @Size(min = 1, max = 500) String resumo, @NotBlank @NotEmpty String sumario, @Min(20) BigDecimal preco, @Min(100) Integer numPag, @NotBlank String isbn, @Future LocalDate dataPublicacao, @NotNull @Valid Autor autor, @NotNull @Valid Categoria categoria) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numPag = numPag;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.autor = autor;
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

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

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    //   public String toString(){
  //      return "Livro: [id= "+ id +"," +
   //             "       Titulo= "+ titulo +"" +
    //            "       Resumo= "+ resumo +"" +
     //           "       Sumario= "+ sumario +" ]";
    //            "       Preco= "+ preco +"" +
     //           "       Numero paginas= "+ numPag + "" +
     //           "       ISBN= "+ isbn +"" +
      //          "       Data Publicação= "+ dataPublicacao +"]";
    //            "       Autor= "+ autor +"" +
      //          "       Categoria= "+ categoria +"    ]";
    //}

}
