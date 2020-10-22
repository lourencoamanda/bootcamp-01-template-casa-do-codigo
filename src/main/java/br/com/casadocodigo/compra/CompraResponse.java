package br.com.casadocodigo.compra;

import br.com.casadocodigo.cupom.CupomAplicado;
import br.com.casadocodigo.entity.Cupom;
import br.com.casadocodigo.entity.Estado;
import br.com.casadocodigo.entity.ItensCarrinho;
import br.com.casadocodigo.entity.Pais;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CompraResponse  {

    private Long id;
    private @Email(message = "Email Inválido") String email;
    private @NotBlank(message  ="Nome Inválido") String nome;
    private @NotBlank(message  ="Sobrenome Inválido") String sobrenome;
    private @NotBlank(message  ="CPF/CPNJ Inválido")String cpfCnpj;
    private @NotBlank(message  ="Endereço Inválido") String endereco;
    private @NotBlank(message  ="Complemento Inválido") String complemento;
    private @NotBlank(message  ="Cidade Inválido") String cidade;
    private @NotBlank(message  ="Telefone Inválido") String telefone;
    private @NotBlank(message  ="CEP Inválido") String cep;
    private @NotNull @Valid Pais pais;
    private @NotNull @Valid Estado estado;
    private BigDecimal total;
    private List<ItensCarrinho> itensCarrinho;
    private CupomAplicado cupomAplicado;
    private BigDecimal valorSemDeconto;
    private BigDecimal valorDesconto;


    public CompraResponse() {
    }

    public CompraResponse(@Email(message = "Email Inválido") String email, @NotBlank(message = "Nome Inválido") String nome, @NotBlank(message = "Sobrenome Inválido") String sobrenome, @NotBlank(message = "CPF/CPNJ Inválido") String cpfCnpj, @NotBlank(message = "Endereço Inválido") String endereco, @NotBlank(message = "Complemento Inválido") String complemento, @NotBlank(message = "Cidade Inválido") String cidade, @NotBlank(message = "Telefone Inválido") String telefone, @NotBlank(message = "CEP Inválido") String cep, @NotNull @Valid Pais pais, @NotNull @Valid Estado estado, BigDecimal total, List<ItensCarrinho> itensCarrinho) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpfCnpj = cpfCnpj;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.telefone = telefone;
        this.cep = cep;
        this.pais = pais;
        this.estado = estado;
        this.total = total;
        this.itensCarrinho = itensCarrinho;
    }

    public CompraResponse(CompraRequest compraRequest) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public BigDecimal getTotal() {
        return total.setScale(2, RoundingMode.HALF_DOWN);
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<ItensCarrinho> getItensCarrinho() {
        return itensCarrinho;
    }

    public void setItensCarrinho(List<ItensCarrinho> itensCarrinho) {
        this.itensCarrinho = itensCarrinho;
    }

    public BigDecimal getValorSemDeconto() {
        return valorSemDeconto;
    }

    public void setValorSemDeconto(BigDecimal valorSemDeconto) {
        this.valorSemDeconto = valorSemDeconto;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }
}
