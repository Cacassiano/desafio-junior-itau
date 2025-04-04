package dev.cacassiano.desafio_itau.services;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.cacassiano.desafio_itau.dtos.EstatisticasDTO;
import dev.cacassiano.desafio_itau.dtos.TransacaoDTO;

@Service
public class TransacaoService {

    private ArrayList<TransacaoDTO> transas = new ArrayList<TransacaoDTO>();
    /* transas methods for tests */
    public void setTransas(ArrayList<TransacaoDTO> i) {
        this.transas = i;
    }

    public ArrayList<TransacaoDTO> getTransas(){
        return this.transas;
    }

    private EstatisticasService service = new EstatisticasService();

    /* Controller Methods */
    public ResponseEntity saveTransacao(TransacaoDTO trans) {
        
        if (trans.valor() < 0 || validateDate(trans.dataHora())){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        } else {
            this.transas.add(trans);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }
    
    public EstatisticasDTO getEstatisticas(int numMin) {
        getLastTransacao(numMin);

        if (this.service.getCount() < 1) {
            return new EstatisticasDTO(0.0, 0.0,0.0, 0.0, 0);
        } else {
            return new EstatisticasDTO(this.service.getSum(), this.service.getAvg(), this.service.getMin(), this.service.getMax(), this.service.getCount());
        }
    
    }

    /* Auxiliar Methods */

    private boolean validateDate(String date) {
        OffsetDateTime reqData = OffsetDateTime.parse(date);
        OffsetDateTime actual = OffsetDateTime.now();
        
        System.out.println("\n" + actual + "\n" + date);
        if(reqData.isBefore(actual)) {
            return false;
        }
        return true;
    }

    private void getLastTransacao(int numMin) {
        OffsetDateTime maxHour = OffsetDateTime.now().minusMinutes(numMin);

        for (TransacaoDTO i : transas) {
            OffsetDateTime dataHora = OffsetDateTime.parse(i.dataHora());
            System.out.println(dataHora);
            if (dataHora.isAfter(maxHour) || dataHora.isEqual(maxHour)) {
                this.service.addList(i.valor());
            }
        }
    }

    
}
