package oop;

import javafx.scene.paint.Paint;

public class Mängija {
    private int järjekorraNr;

    private Paint nupuVärv;

    public Paint getNupuVärv() {
        return nupuVärv;
    }

    public Mängija(Paint nupuVärv, int järjekorraNr) {
        this.nupuVärv = nupuVärv;
        this.järjekorraNr = järjekorraNr;
    }

    @Override
    public String toString() {
        return "Mängija{" +
                "mängija nr=" + järjekorraNr +
                '}';
    }
}
