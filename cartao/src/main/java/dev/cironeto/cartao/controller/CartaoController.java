package dev.cironeto.cartao.controller;

import dev.cironeto.cartao.domain.Cartao;
import dev.cironeto.cartao.request.CartaoRequestBody;
import dev.cironeto.cartao.service.CartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartao")
@RequiredArgsConstructor
public class CartaoController {

    private final CartaoService service;

    @GetMapping
    public String status(){
        return "OK";
    }

    @PostMapping
    public ResponseEntity cadastra(@RequestBody CartaoRequestBody requestBody){
        Cartao cartao = requestBody.toModel();
        service.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam Long renda){
        List<Cartao> cartoes = service.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(cartoes);
    }
}
