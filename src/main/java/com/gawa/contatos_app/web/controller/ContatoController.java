package com.gawa.contatos_app.web.controller;

import com.gawa.contatos_app.entity.Contato;
import com.gawa.contatos_app.service.ContatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/contatos")
public class ContatoController {

    private final ContatoService contatoService;

    @PostMapping
    public ResponseEntity<Contato> create(@RequestBody Contato contato){
        Contato cont = contatoService.salvar(contato);
        return ResponseEntity.status(HttpStatus.CREATED).body(cont);
    }
}
