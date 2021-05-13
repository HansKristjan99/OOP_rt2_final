package oop;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Käik {

    public Käik() {
    }

    public List<String> võimalikudKäigud(String täht) {
        List<String> lubatud = new ArrayList<>();

        if (täht.equals("A")) {
            lubatud.addAll(Arrays.asList("B", "J"));
        } else if (täht.equals("B")) {
            lubatud.addAll(Arrays.asList("A", "C", "E"));
        } else if (täht.equals("C")) {
            lubatud.addAll(Arrays.asList("B", "O"));
        } else if (täht.equals("D")) {
            lubatud.addAll(Arrays.asList("E", "K"));
        } else if (täht.equals("E")) {
            lubatud.addAll(Arrays.asList("B", "F", "H", "D"));
        } else if (täht.equals("F")) {
            lubatud.addAll(Arrays.asList("E", "N"));
        } else if (täht.equals("G")) {
            lubatud.addAll(Arrays.asList("H", "L"));
        } else if (täht.equals("H")) {
            lubatud.addAll(Arrays.asList("E", "G", "I"));
        } else if (täht.equals("I")) {
            lubatud.addAll(Arrays.asList("H", "M"));
        } else if (täht.equals("J")) {
            lubatud.addAll(Arrays.asList("A", "K", "V"));
        } else if (täht.equals("K")) {
            lubatud.addAll(Arrays.asList("J", "D", "L", "S"));
        } else if (täht.equals("L")) {
            lubatud.addAll(Arrays.asList("K", "G", "P"));
        } else if (täht.equals("M")) {
            lubatud.addAll(Arrays.asList("I", "R", "N"));
        } else if (täht.equals("N")) {
            lubatud.addAll(Arrays.asList("M", "F", "O", "U"));
        } else if (täht.equals("O")) {
            lubatud.addAll(Arrays.asList("C", "N", "X"));
        } else if (täht.equals("P")) {
            lubatud.addAll(Arrays.asList("L", "Q"));
        } else if (täht.equals("Q")) {
            lubatud.addAll(Arrays.asList("P", "R", "T"));
        } else if (täht.equals("R")) {
            lubatud.addAll(Arrays.asList("M", "Q"));
        } else if (täht.equals("S")) {
            lubatud.addAll(Arrays.asList("K", "T"));
        } else if (täht.equals("T")) {
            lubatud.addAll(Arrays.asList("Q", "U", "W", "S"));
        } else if (täht.equals("U")) {
            lubatud.addAll(Arrays.asList("N", "T"));
        } else if (täht.equals("V")) {
            lubatud.addAll(Arrays.asList("J", "W"));
        } else if (täht.equals("W")) {
            lubatud.addAll(Arrays.asList("V", "T", "X"));
        } else if (täht.equals("X")) {
            lubatud.addAll(Arrays.asList("W", "O"));
        }

        return lubatud;
    }




    public boolean kasVaba(GraafilineLaud laud, String täht) {
        Mängija tühiMängija = new Mängija(Color.WHITE,6);
        if (täht.equals("A")) {
            return laud.getKohad().get(0).getOlek().equals(tühiMängija);
        } else if (täht.equals("B")) {
            return laud.getKohad().get(1).getOlek().equals(tühiMängija);
        } else if (täht.equals("C")) {
            return laud.getKohad().get(2).getOlek().equals(tühiMängija);
        } else if (täht.equals("D")) {
            return laud.getKohad().get(3).getOlek().equals(tühiMängija);
        } else if (täht.equals("E")) {
            return laud.getKohad().get(4).getOlek().equals(tühiMängija);
        } else if (täht.equals("F")) {
            return laud.getKohad().get(5).getOlek().equals(tühiMängija);
        } else if (täht.equals("G")) {
            return laud.getKohad().get(6).getOlek().equals(tühiMängija);
        } else if (täht.equals("H")) {
            return laud.getKohad().get(7).getOlek().equals(tühiMängija);
        } else if (täht.equals("I")) {
            return laud.getKohad().get(8).getOlek().equals(tühiMängija);
        } else if (täht.equals("J")) {
            return laud.getKohad().get(9).getOlek().equals(tühiMängija);
        } else if (täht.equals("K")) {
            return laud.getKohad().get(10).getOlek().equals(tühiMängija);
        } else if (täht.equals("L")) {
            return laud.getKohad().get(11).getOlek().equals(tühiMängija);
        } else if (täht.equals("M")) {
            return laud.getKohad().get(12).getOlek().equals(tühiMängija);
        } else if (täht.equals("N")) {
            return laud.getKohad().get(13).getOlek().equals(tühiMängija);
        } else if (täht.equals("O")) {
            return laud.getKohad().get(14).getOlek().equals(tühiMängija);
        } else if (täht.equals("P")) {
            return laud.getKohad().get(15).getOlek().equals(tühiMängija);
        } else if (täht.equals("Q")) {
            return laud.getKohad().get(16).getOlek().equals(tühiMängija);
        } else if (täht.equals("R")) {
            return laud.getKohad().get(17).getOlek().equals(tühiMängija);
        } else if (täht.equals("S")) {
            return laud.getKohad().get(18).getOlek().equals(tühiMängija);
        } else if (täht.equals("T")) {
            return laud.getKohad().get(19).getOlek().equals(tühiMängija);
        } else if (täht.equals("U")) {
            return laud.getKohad().get(20).getOlek().equals(tühiMängija);
        } else if (täht.equals("V")) {
            return laud.getKohad().get(21).getOlek().equals(tühiMängija);
        } else if (täht.equals("W")) {
            return laud.getKohad().get(22).getOlek().equals(tühiMängija);
        } else if (täht.equals("X")) {
            return laud.getKohad().get(23).getOlek().equals(tühiMängija);
        } else {
            return true;
        }
    }

    public boolean lubatudKäik(Seis seis, Mängija mängija, String sihtKoht) {
        boolean lubatud = false;
        if (kasVaba(seis, sihtKoht)) {
            for (String s : võimalikudKäigud(sihtKoht)) {
                if (seis.mängijaNupud(mängija).contains(s)) {
                    lubatud = true;
                }
            }
        }
        return lubatud;
    }
}
