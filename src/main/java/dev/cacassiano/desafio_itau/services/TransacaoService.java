package dev.cacassiano.desafio_itau.services;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.cacassiano.desafio_itau.dtos.TransacaoDTO;

@Service
public class TransacaoService {

    private ArrayList<TransacaoDTO> transas = new ArrayList<TransacaoDTO>();

    /* Endpoint "/transacoes" */
    public ResponseEntity saveTransacao(TransacaoDTO trans) {
        
        if (trans.valor() < 0 || validateDate(trans.dataHora())){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        } else {
            this.transas.add(trans);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }
    
    private boolean validateDate(String date) {
        OffsetDateTime reqData = OffsetDateTime.parse(date);
        OffsetDateTime actual = OffsetDateTime.now();
        
        System.out.println("\n" + actual + "\n" + date);
        if(reqData.isBefore(actual)) {
            return false;
        }
        return true;
    }



    
}
