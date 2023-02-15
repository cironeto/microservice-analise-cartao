package dev.cironeto.cartao.request;

import dev.cironeto.cartao.domain.ClienteCartao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClienteCartaoResponseBody {

    private String nome;
    private String bandeira;
    private BigDecimal limite;

    public ClienteCartaoResponseBody(ClienteCartao entity){
        this.nome = entity.getCartao().getNome();
        this.bandeira = entity.getCartao().getBandeira().toString();
        this.limite = entity.getCartao().getLimiteBasico();
    }
}
