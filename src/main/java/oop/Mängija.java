package oop;

import javafx.scene.paint.Paint;

public class Mängija {
    private Paint nupuVärv;

    public Paint getNupuVärv() {
        return nupuVärv;
    }

    public Mängija(Paint nupuVärv) {
        this.nupuVärv = nupuVärv;
    }

    @Override
    public String toString() {
        return "Mängija{" +
                "nupuVärv=" + nupuVärv +
                '}';
    }
}
