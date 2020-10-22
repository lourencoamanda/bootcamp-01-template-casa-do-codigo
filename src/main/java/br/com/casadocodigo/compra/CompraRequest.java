package br.com.casadocodigo.compra;

import br.com.casadocodigo.cupom.CupomAplicado;
import br.com.casadocodigo.entity.*;
import br.com.casadocodigo.repositories.CupomRepository;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CompraRequest {

    @Autowired
    private CupomRepository cupomRepository;

    private @Email(message = "Email Inválido") String email;
    private @NotBlank(message  ="Nome Inválido") String nome;
    private @NotBlank(message  ="Sobrenome Inválido") String sobrenome;
    private @NotBlank(message  ="CPF/CPNJ Inválido")String cpfCnpj;
    private @NotBlank(message  ="Endereço Inválido") String endereco;
    private @NotBlank(message  ="Complemento Inválido") String complemento;
    private @NotBlank(message  ="Cidade Inválido") String cidade;
    private @NotBlank(message  ="Telefone Inválido") String telefone;
    private @NotBlank(message  ="CEP Inválido") String cep;
    private @NotNull(message  ="País Inválido") Long idPais;
    private Long idEstado;
    private String codigoCupom;

    private @Min(value =1, message = "Total mínimo é 1 ") BigDecimal total;
    private List<ItensCarrinho> itensCarrinho = new ArrayList<>();

    private CupomAplicado cupomAplicado;

    private Cliente cliente;

    private BigDecimal totalSemDesconto;


    public CompraRequest() {
    }

    public CompraRequest(@Email(message = "Email Inválido") String email, @NotBlank(message = "Nome Inválido") String nome, @NotBlank(message = "Sobrenome Inválido") String sobrenome, @NotBlank(message = "CPF/CPNJ Inválido") String cpfCnpj, @NotBlank(message = "Endereço Inválido") String endereco, @NotBlank(message = "Complemento Inválido") String complemento, @NotBlank(message = "Cidade Inválido") String cidade, @NotBlank(message = "Telefone Inválido") String telefone, @NotBlank(message = "CEP Inválido") String cep, @NotNull(message = "País Inválido") Long idPais, Long idEstado, String codigoCupom, @Min(value = 1, message = "Total mínimo é 1 ") BigDecimal total, List<ItensCarrinho> itensCarrinho) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpfCnpj = cpfCnpj;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.telefone = telefone;
        this.cep = cep;
        this.idPais = idPais;
        this.idEstado = idEstado;
        this.codigoCupom = codigoCupom;
        this.total = total;
        this.itensCarrinho = itensCarrinho;
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

    public Long getIdPais() {
        return idPais;
    }

    public void setIdPais(Long idPais) {
        this.idPais = idPais;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public BigDecimal getTotal() {
        return total;
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

    public String getCodigoCupom() {
        return codigoCupom;
    }

    public void setCodigoCupom(String codigoCupom) {
        this.codigoCupom = codigoCupom;
    }

    public BigDecimal getTotalSemDesconto() {
        return totalSemDesconto;
    }

    public void setTotalSemDesconto(BigDecimal totalSemDesconto) {
        this.totalSemDesconto = totalSemDesconto;
    }

    public boolean validaCpfCpnj() {
        Assert.hasLength(this.cpfCnpj, "{ CPF/CPNJ Inválido }");

        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(this.cpfCnpj, null)
                || cnpjValidator.isValid(this.cpfCnpj, null);
    }

    public Cliente toModel(EntityManager manager) {

        @NotNull Pais pais = manager.find(Pais.class, idPais);
        @NotNull Estado estado = manager.find(Estado.class, idEstado);

        Assert.state(pais!=null,"Pais não possui cadastro nas bases de dados: "+idPais );
        Assert.state(estado!=null,"Estado não possui cadastro nas bases de dados: "+idEstado);

        return new Cliente(email,nome,sobrenome,cpfCnpj,endereco,complemento,cidade,telefone,cep, pais, estado, total, totalSemDesconto, itensCarrinho);
    }

}
