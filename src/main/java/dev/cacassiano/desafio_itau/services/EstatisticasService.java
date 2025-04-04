package dev.cacassiano.desafio_itau.services;

import java.util.ArrayList;


public class EstatisticasService {
    
    private ArrayList<Double> list = new ArrayList<Double>();


    public void addList(Double valor) {
        this.list.add(valor);
    }

    public int getCount() {
        return this.list.size();
    }

    public Double getSum() {
        Double sum = 0.0;
        for (Double i : this.list) {
            sum += i;
        }
        return sum;
    }

    public Double getAvg() {
        return getSum()/getCount();
    }

    public Double getMin() {
        Double min = this.list.get(0);
        for (Double i : this.list) {
            if(i < min) {
                min = i;
            }
        }
        return min;
    } 

    public Double getMax() {
        Double max = this.list.get(0);
        for (Double i : this.list) {
            if(i > max) {
                max = i;
            }
        }
        return max;
    }
}
