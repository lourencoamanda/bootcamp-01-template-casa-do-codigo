package br.com.casadocodigo.compra;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoRequest {

    private @Min(value =1, message = "Total mínimo é 1 ") BigDecimal total;
    private List<ItensCarrinhoRequest> itens = new ArrayList<>();

    public CarrinhoRequest(){
    }

    public CarrinhoRequest(@Min(value = 1, message = "Total mínimo é 1 ") BigDecimal total, List<ItensCarrinhoRequest> itens) {
        this.total = total;
        this.itens = itens;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<ItensCarrinhoRequest> getItens() {
        return itens;
    }

    public void setItens(List<ItensCarrinhoRequest> itens) {
        this.itens = itens;
    }



    }
