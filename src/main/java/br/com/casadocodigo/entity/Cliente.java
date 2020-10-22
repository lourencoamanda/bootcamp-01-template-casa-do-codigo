package br.com.casadocodigo.entity;

import br.com.casadocodigo.compra.CompraRequest;
import br.com.casadocodigo.cupom.CupomAplicado;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Entity
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    private @NotNull @Valid Pais pais;

    @ManyToOne
    private @NotNull @Valid Estado estado;

    private BigDecimal total;

    private BigDecimal totalSemDesconto;

    @ElementCollection
    @CollectionTable(name="cliente_pedido")
    private List<ItensCarrinho> itensCarrinho;

    @Embedded
    private CupomAplicado cupomAplicado;


    public Cliente() {
    }

    public Cliente(@Email(message = "Email Inválido") String email, @NotBlank(message = "Nome Inválido") String nome, @NotBlank(message = "Sobrenome Inválido") String sobrenome, @NotBlank(message = "CPF/CPNJ Inválido") String cpfCnpj, @NotBlank(message = "Endereço Inválido") String endereco, @NotBlank(message = "Complemento Inválido") String complemento, @NotBlank(message = "Cidade Inválido") String cidade, @NotBlank(message = "Telefone Inválido") String telefone, @NotBlank(message = "CEP Inválido") String cep, @NotNull @Valid Pais pais, @NotNull @Valid Estado estado, BigDecimal total, BigDecimal totalSemDesconto, List<ItensCarrinho> itensCarrinho) {
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
        this.totalSemDesconto = totalSemDesconto;
        this.itensCarrinho = itensCarrinho;
    }

    public Cliente(CompraRequest compraRequest) {
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

    public BigDecimal getTotalSemDesconto() {
        return totalSemDesconto;
    }

    public void setTotalSemDesconto(BigDecimal totalSemDesconto) {
        this.totalSemDesconto = totalSemDesconto;
    }

    public void aplicaCupom(Cupom cupom) {
        this.cupomAplicado = new CupomAplicado(cupom);
    }

    public void toModelCupom(String codigoCupom,EntityManager manager){

        List<Cupom> cupomValido = manager
                .createNativeQuery("SELECT * FROM CUPOM WHERE CODIGO_CUPOM = :codigo", Cupom.class)
                .setParameter("codigo", codigoCupom)
                .getResultList();

        aplicaCupom(cupomValido.get(0));

    }
}
