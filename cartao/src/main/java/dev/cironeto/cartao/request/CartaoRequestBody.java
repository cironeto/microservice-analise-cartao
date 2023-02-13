package dev.cironeto.cartao.request;

import dev.cironeto.cartao.domain.BandeiraCartao;
import dev.cironeto.cartao.domain.Cartao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoRequestBody {

    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;

    public Cartao toModel(){
        return new Cartao(nome, bandeira, renda, limiteBasico);
    }


}
