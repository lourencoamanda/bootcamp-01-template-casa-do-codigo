package br.com.casadocodigo.estado;

import br.com.casadocodigo.compra.CompraRequest;
import br.com.casadocodigo.entity.Estado;
import br.com.casadocodigo.entity.Pais;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ValidaEstadoPertencePais implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> clazz) {
        return CompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        CompraRequest request = (CompraRequest) target;

        Pais pais = manager.find(Pais.class, request.getIdPais());
        Estado estado = manager.find(Estado.class, request.getIdEstado());

        if(!estado.pertenceAPais(pais)) {
           errors.rejectValue("idEstado",null,"este estado não é o do país selecionado");
        }

    }
}
