package br.com.casadocodigo.cupom;

import br.com.casadocodigo.entity.Cupom;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Embeddable
public class CupomAplicado {

    @ManyToOne
    private Cupom cupom;

    private @Positive(message  ="Percentual Desconto Inválido") @NotNull BigDecimal percentualDescontoCompra;
    private @Future (message  ="Data Validade não está no futuro")@NotNull LocalDate validadeCompra;

    @Deprecated
    public CupomAplicado() {
    }

    public CupomAplicado(Cupom cupom) {
        this.cupom = cupom;
        this.percentualDescontoCompra = cupom.getPercentualDesconto();
        this.validadeCompra = cupom.getValidade();
    }

    public Cupom getCupom() {
        return cupom;
    }

    public void setCupom(Cupom cupom) {
        this.cupom = cupom;
    }

    public BigDecimal getPercentualDescontoCompra() {
        return percentualDescontoCompra;
    }

    public void setPercentualDescontoCompra(BigDecimal percentualDescontoCompra) {
        this.percentualDescontoCompra = percentualDescontoCompra;
    }

    public LocalDate getValidadeCompra() {
        return validadeCompra;
    }

    public void setValidadeCompra(LocalDate validadeCompra) {
        this.validadeCompra = validadeCompra;
    }
}
