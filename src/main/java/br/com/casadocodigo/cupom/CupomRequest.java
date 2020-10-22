package br.com.casadocodigo.cupom;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class   CupomRequest {

    private @NotBlank(message  ="Código Cupom Inválido") String codigoCupom;
    private @Positive(message  ="Percentual Desconto Inválido") @NotNull BigDecimal percentualDesconto;
    private @Future (message  ="Data Validade não está no futuro")@NotNull LocalDate validade;

    public CupomRequest() {
    }

    public CupomRequest(@NotBlank(message = "Código Cupom Inválido") String codigoCupom, @Positive(message = "Percentual Desconto Inválido") @NotNull BigDecimal percentualDesconto, @Future(message = "Data Validade não está no futuro") @NotNull LocalDate validade) {
        this.codigoCupom = codigoCupom;
        this.percentualDesconto = percentualDesconto;
        this.validade = validade;
    }

    public String getCodigoCupom() {
        return codigoCupom;
    }

    public void setCodigoCupom(String codigoCupom) {
        this.codigoCupom = codigoCupom;
    }

    public BigDecimal getPercentualDesconto() {
        return percentualDesconto;
    }

    public void setPercentualDesconto(BigDecimal percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }
}
