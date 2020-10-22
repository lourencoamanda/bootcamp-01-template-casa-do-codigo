package br.com.casadocodigo.estado;

import br.com.casadocodigo.entity.Estado;
import br.com.casadocodigo.entity.Pais;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EstadoRequest {

    private @NotBlank (message = "{ Nome do estado Inválido }") String nome;
    private @NotNull(message = "{ Id do país Inválido }") Long idPais;

    public EstadoRequest(){
    }

    public EstadoRequest(@NotBlank(message = "{ Nome do estado Inválido }") String nome, @NotNull(message = "{ Id do país Inválido }") Long idPais) {
        this.nome = nome;
        this.idPais = idPais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdPais() {
        return idPais;
    }

    public void setIdPais(Long idPais) {
        this.idPais = idPais;
    }

    public Estado toModel(EntityManager manager) {
        return new Estado(nome, manager.find(Pais.class, idPais));
    }

}
