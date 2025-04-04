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

import dev.cacassiano.desafio_itau.dtos.EstatisticasDTO;
import dev.cacassiano.desafio_itau.dtos.TransacaoDTO;
import dev.cacassiano.desafio_itau.services.EstatisticasService;
import dev.cacassiano.desafio_itau.services.TransacaoService;

@SpringBootTest
public class TransacaoControllerTest {

    @Autowired
    private TransacaoController controller;
    @Autowired
    private TransacaoService service;
     
    EstatisticasService estatisticasService = new EstatisticasService();

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

    @Test
    @DisplayName("Deve mostrar um DTO completo")
    public void showEstaisticasCase1() {

        String data = OffsetDateTime.now() +"";
        TransacaoDTO transacao = new TransacaoDTO(125.00, data);
        this.controller.insertTransaction(transacao);
        System.out.println(service.getTransas().get(0));

        data = OffsetDateTime.now().minusSeconds(45) +"";
        transacao = new TransacaoDTO(12.50, data);
        this.controller.insertTransaction(transacao);
        System.out.println(service.getTransas().get(1));

        data = OffsetDateTime.now().minusSeconds(50) +"";
        transacao = new TransacaoDTO(12.50, data);
        this.controller.insertTransaction(transacao);
        System.out.println(service.getTransas().get(2));

        data = OffsetDateTime.now().minusMinutes(1) +"";
        transacao = new TransacaoDTO(10.00, data);
        this.controller.insertTransaction(transacao);
        System.out.println(service.getTransas().get(3));

        data = OffsetDateTime.now().minusMinutes(2) +"";
        transacao = new TransacaoDTO(5.00, data);
        this.controller.insertTransaction(transacao);
        System.out.println(service.getTransas().get(4));

        data = OffsetDateTime.now().minusSeconds(15) +"";
        transacao = new TransacaoDTO(50.00, data);
        this.controller.insertTransaction(transacao);
        System.out.println(service.getTransas().get(5));

        EstatisticasDTO esperado = new EstatisticasDTO(200.0, 50.0, 12.5, 125.0, 4);
        assertEquals(esperado,this.controller.getEstatisticas());
    }




}
