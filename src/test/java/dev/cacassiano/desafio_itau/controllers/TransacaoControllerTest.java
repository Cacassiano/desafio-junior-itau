package dev.cacassiano.desafio_itau.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dev.cacassiano.desafio_itau.dtos.TransacaoDTO;

@SpringBootTest
public class TransacaoControllerTest {

    @Autowired
    private TransacaoController trans;

    @Test 
    @DisplayName("Caso onde tudo da certo")
    public void saveTransacaoCase1(){
        String data = OffsetDateTime.now() +"";
        TransacaoDTO transacao = new TransacaoDTO(125.00, data);

        assertEquals(ResponseEntity.status(HttpStatus.CREATED).build(), this.trans.insertTransaction(transacao));


    }
    
}
