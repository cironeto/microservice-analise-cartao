package dev.cironeto.cartao.controller;

import dev.cironeto.cartao.domain.Cartao;
import dev.cironeto.cartao.domain.ClienteCartao;
import dev.cironeto.cartao.request.ClienteCartaoResponseBody;
import dev.cironeto.cartao.request.CartaoRequestBody;
import dev.cironeto.cartao.service.CartaoService;
import dev.cironeto.cartao.service.ClienteCartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cartao")
@RequiredArgsConstructor
public class CartaoController {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    @GetMapping
    public String status(){
        return "OK";
    }

    @PostMapping
    public ResponseEntity cadastra(@RequestBody CartaoRequestBody requestBody){
        Cartao cartao = requestBody.toModel();
        this.cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam Long renda){
        List<Cartao> cartoes = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(cartoes);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<ClienteCartaoResponseBody>> findByCpf(@RequestParam String cpf){
        List<ClienteCartao> list = clienteCartaoService.findByCpf(cpf);
        return ResponseEntity.ok(list.stream().map(ClienteCartaoResponseBody::new).collect(Collectors.toList()));
    }
}
