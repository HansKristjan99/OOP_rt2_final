package oop;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

// selles klassis loome kohtadele kuuluvad objektid
public class Koht {
    private String nimi;
    private Mängija olek;
    private Circle ring;
    private int indeks;

    public Koht(String nimi, Mängija  olek, Circle ring, int indeks) {
        this.nimi = nimi;
        this.olek = olek;
        this.ring = ring;
        this.indeks = indeks;
    }

    public Mängija getOlek() {
        return olek;
    }

    public String getNimi() {
        return nimi;
    }

    public Circle getRing() {
        return ring;
    }

    public int getIndeks() {
        return indeks;
    }

    public void setOlek(Mängija olek) {
        this.olek = olek;
        setRingPaint(olek.getNupuVärv());

    }

    public void setRingPaint(Paint paint) {
        this.ring.setFill(paint);
    }

    public Paint getRingPaint() {
        return ring.getFill();
    }


    public void setRingLäbipaistvus(boolean olemas) {
        if (olemas) {
            this.ring.setOpacity(1.0);
        }
        else {
            this.ring.setOpacity(0.0);
        }
    }

    @Override
    public String toString() {
        return "Koht: " +
                "nimi='" + nimi + "indeks" + indeks;
    }
}