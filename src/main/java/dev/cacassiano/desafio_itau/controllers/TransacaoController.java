package dev.cacassiano.desafio_itau.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.cacassiano.desafio_itau.dtos.TransacaoDTO;
import dev.cacassiano.desafio_itau.services.TransacaoService;

@RestController
public class TransacaoController {
    
    @Autowired
    private TransacaoService service;
    
    @PostMapping("/transacao")
    public ResponseEntity insertTransaction(@RequestBody TransacaoDTO trans){
        if(trans.valor() != null & trans.dataHora() != null) {
            return this.service.saveTransacao(trans);
        }
        return ResponseEntity.badRequest().build();
    }

    
}
