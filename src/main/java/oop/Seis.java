package oop;

public class Seis extends GraafilineLaud {
    public Seis() {
    }

    public void eemaldaNupp(String koht) {
        for (Koht k : getKohad()) {
            if (k.getNimi().equals(koht)) {
                k.setOlek(null);
            }
        }
    }
}
