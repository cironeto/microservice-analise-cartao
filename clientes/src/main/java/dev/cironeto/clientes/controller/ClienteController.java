package dev.cironeto.clientes.controller;

import dev.cironeto.clientes.domain.Cliente;
import dev.cironeto.clientes.dto.ClienteRequestBody;
import dev.cironeto.clientes.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public String status(){
        log.info("Verificando status de ms-cliente");
        return "OK";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteRequestBody requestBody){
        var cliente = requestBody.toModel();
        clienteService.save(cliente);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(value = "/dados")
    public ResponseEntity dadosClinte(@RequestParam String cpf){
        var cliente = clienteService.getByCpf(cpf);
        if(cliente.isEmpty()){
           return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente);
    }
}
