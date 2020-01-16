package model;

import java.util.ArrayList;

public class frota {

    private ArrayList<barco> barcos;

    public frota() {
        this.barcos = new ArrayList<>(0);
        this.fill();
    }

    private void fill() {

        for (int i = 1; i <= 8; i++) {
            barco barco1 = new barco(i);
            this.barcos.add(barco1);
        }
    }

    public ArrayList<barco> getBarcos() {
        return this.barcos;
    }

}