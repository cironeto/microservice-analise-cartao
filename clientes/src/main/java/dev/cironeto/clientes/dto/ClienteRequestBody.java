package dev.cironeto.clientes.dto;

import dev.cironeto.clientes.domain.Cliente;
import lombok.Data;

@Data
public class ClienteRequestBody {
    private String cpf;
    private String nome;
    private Integer idade;

    public Cliente toModel(){
        return new Cliente(cpf, nome, idade);
    }
}
