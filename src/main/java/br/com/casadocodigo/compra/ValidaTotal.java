package br.com.casadocodigo.compra;

import br.com.casadocodigo.entity.Livro;
import br.com.casadocodigo.repositories.CupomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@Component
public class ValidaTotal implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CupomRepository cupomRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return ;
        }

        BigDecimal total = new BigDecimal(0);

        CompraRequest request = (CompraRequest) target;

        for(int qtdLivros=0; qtdLivros < request.getItensCarrinho().size(); qtdLivros++) {
            Livro buscaLivro = manager.find(Livro.class, request.getItensCarrinho().get(qtdLivros).getIdLivro());

            Assert.state(buscaLivro!=null,"Id do livro não possui cadastro nas bases de dados: ");

            total = total.add(buscaLivro.getPreco());

        }
        if (!request.getTotal().equals(total)){
            errors.rejectValue("Total",null, "Valor total do carrinho Inválido");
        }

    }
}
