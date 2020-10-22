package br.com.casadocodigo.compra;

import br.com.casadocodigo.cupom.CupomAplicado;
import br.com.casadocodigo.entity.Cliente;
import br.com.casadocodigo.entity.Cupom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class ValidaCupom implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> clazz) {
        return CompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return ;
        }

        CompraRequest request = (CompraRequest) target;
        BigDecimal valorDesconto = new BigDecimal(0);
        BigDecimal valorTotal = new BigDecimal(0);

        if(!request.getCodigoCupom().isEmpty()){

            request.setTotalSemDesconto(request.getTotal());

            List<Cupom> cupomValido = manager
                    .createNativeQuery("SELECT * FROM CUPOM WHERE CODIGO_CUPOM = :codigo", Cupom.class)
                    .setParameter("codigo", request.getCodigoCupom())
                    .getResultList();

            Cupom cupom = cupomValido.get(0);

            if(!cupom.valido()){
                errors.rejectValue("codigoCupom", null, "Este cupom não é mais válido");
            }
            valorDesconto = request.getTotal().multiply(cupom.getPercentualDesconto().setScale(2, RoundingMode.HALF_DOWN));
            request.setTotal(request.getTotal().subtract(valorDesconto).setScale(2, RoundingMode.HALF_DOWN));

        }
    }
}
