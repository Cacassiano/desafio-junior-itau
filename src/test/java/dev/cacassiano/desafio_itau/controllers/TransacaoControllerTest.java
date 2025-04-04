package dev.cacassiano.desafio_itau.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dev.cacassiano.desafio_itau.dtos.TransacaoDTO;
import dev.cacassiano.desafio_itau.services.TransacaoService;

@SpringBootTest
public class TransacaoControllerTest {

    @Autowired
    private TransacaoController controller;
    @Autowired
    private TransacaoService service;

    @Test 
    @DisplayName("Caso onde tudo da certo")
    public void saveTransacaoCase1(){
        String data = OffsetDateTime.now() +"";
        TransacaoDTO transacao = new TransacaoDTO(125.00, data);

        assertEquals(ResponseEntity.status(HttpStatus.CREATED).build(), this.controller.insertTransaction(transacao));
    }
    
    @Test 
    @DisplayName("Caso onde tudo da certo")
    public void saveTransacaoCase2(){
        String data = OffsetDateTime.now() +"";
        TransacaoDTO transacao = new TransacaoDTO(-1.0, data);

        assertNotEquals(ResponseEntity.status(HttpStatus.CREATED).build(), this.controller.insertTransaction(transacao));
    }

    @Test
    public void deleteTransacoesCase1() {
        String data = OffsetDateTime.now() +"";
        TransacaoDTO transacao = new TransacaoDTO(125.00, data);

        this.controller.insertTransaction(transacao);
        this.controller.deleteTransacoes();

        assertEquals( new ArrayList<TransacaoDTO>(),this.service.getTransas());
    }



}
