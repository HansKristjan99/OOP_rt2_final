package oop;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class GraafilineLaud extends Application {
    Image background = new Image("board.png");
    List<Koht> kohad = new ArrayList<>();
    List<Mängija> mängijad = new ArrayList<>();
    boolean esimeseMängijaKord = true;
    int mängufaas = 0;
    Koht aktiivseNupuKoht;
    int nuppeLisatud = 0;
    boolean järgmineKäikVõtmine1 = false;
    boolean järgmineKäikVõtmine2 = false;
    Color taust = Color.BLACK;
    int käiguNr = 0;

    private ImagePattern punane = new ImagePattern(new Image("punane_nupp.png"));
    private ImagePattern must = new ImagePattern(new Image("must_nupp.png"));

    public void setBackground(String nimi) {
        this.background = new Image(nimi);
    }

    public void setPunane(String nimi) {
        this.punane = new ImagePattern(new Image(nimi));
    }

    public void setMust(String nimi) {
        this.must = new ImagePattern(new Image(nimi));
    }

    public void setTaust(Color taust) {
        this.taust = taust;
    }
    public void kirjutaLogiFaili (String tekst) throws LogiFailiEiLeitudErind {
        try {
            File file =new File("logifail.txt");
            if(!file.exists()){
                throw new LogiFailiEiLeitudErind(file);
            }
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(tekst);
            bw.close();
        } catch (Exception e){
            System.out.println("Logifail loodi");
        }
    }

    public List<String> mängijaNupud(Mängija mängija) {
        List<String> mängijaNupud = new ArrayList<>();
        for (Koht k : kohad) {
            if (k.getOlek().equals(mängija)) {
                mängijaNupud.add(k.getNimi());
            }
        }
        return mängijaNupud;
    }

    public boolean käikLisabKolmiku(List<Koht> kohad, Koht koht, Mängija mängija) {
        List<int[]> vaadeldavadIndeksid = new ArrayList<>();
        for (int[] ints : kolmikud) {
            for (int anInt : ints) {
                if (anInt == koht.getIndeks()) {
                    vaadeldavadIndeksid.add(ints);
                    break;
                }
            }
        }
        for (int[] ints : vaadeldavadIndeksid) {
            for (int i = 0; i < ints.length; i++) {
                if (ints[i] == kohad.indexOf(koht)) {
                    ints[i] = -1;
                }
            }
        }


        for (int[] ints : vaadeldavadIndeksid) {
            boolean kolmik = true;

            for (int i = 0; i < ints.length; i++) {
                if (ints[i] != -1) {
                    if (!kohad.get(ints[i]).getOlek().equals(mängija)) {
                        kolmik = false;
                        break;
                    }
                }
            }
            if (kolmik) {
                return kolmik;
            }
        }
        return false;
    }

    public boolean käikLisabKolmiku2(List<Koht> kohad, Koht koht, Koht vanaKoht, Mängija mängija) {
        List<int[]> vaadeldavadIndeksid = new ArrayList<>();
        for (int[] ints : kolmikud) {
            for (int anInt : ints) {
                if (anInt == koht.getIndeks()) {
                    vaadeldavadIndeksid.add(ints);
                    break;
                }
            }
        }
        for (int[] ints : vaadeldavadIndeksid) {
            for (int i = 0; i < ints.length; i++) {
                if (ints[i] == kohad.indexOf(koht)) {
                    ints[i] = -1;
                }
            }
        }


        for (int[] ints : vaadeldavadIndeksid) {
            boolean kolmik = true;
            for (int i = 0; i < ints.length; i++) {
                if (ints[i] != -1) {
                    if (!kohad.get(ints[i]).getOlek().equals(mängija) || ints[i] == vanaKoht.getIndeks()) {
                        kolmik = false;
                        break;
                    }
                }
            }
            if (kolmik) {

                return kolmik;
            }
        }
        return false;
    }


    public List<int[]> kolmikud = new ArrayList<>();

    public void täidaKolmikud() {
        kolmikud.clear();
        //Horisontaalsed
        kolmikud.add(new int[]{0, 1, 2});
        kolmikud.add(new int[]{3, 4, 5});
        kolmikud.add(new int[]{6, 7, 8});
        kolmikud.add(new int[]{9, 10, 11});
        kolmikud.add(new int[]{12, 13, 14});
        kolmikud.add(new int[]{15, 16, 17});
        kolmikud.add(new int[]{18, 19, 20});
        kolmikud.add(new int[]{21, 22, 23});
        //Vertikaalsed
        kolmikud.add(new int[]{0, 9, 21});
        kolmikud.add(new int[]{3, 10, 18});
        kolmikud.add(new int[]{6, 11, 15});
        kolmikud.add(new int[]{1, 4, 7});
        kolmikud.add(new int[]{16, 19, 22});
        kolmikud.add(new int[]{8, 12, 17});
        kolmikud.add(new int[]{5, 13, 20});
        kolmikud.add(new int[]{2, 14, 23});

    }


    public Group setUp() {
        Group juur = new Group(); // luuakse juur
        Canvas canvas = new Canvas(600, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(background, 0.0, 0.0, canvas.getHeight(), canvas.getWidth());
        juur.getChildren().add(canvas);

        Text info = new Text("Mängu 1. faas");
        if (taust == Color.WHITE) {
            info.setFill(Color.BLACK);
        } else {
            info.setFill(Color.WHITE);
        }

        info.setLayoutX(700);
        info.setLayoutY(150);
        juur.getChildren().add(info);

        Map<String, double[]> asukohadLaual = new HashMap<>();
        asukohadLaual.put("A", new double[]{77.002, 76.361});
        asukohadLaual.put("B", new double[]{301.321, 76.361});
        asukohadLaual.put("C", new double[]{522.676, 76.361});
        asukohadLaual.put("D", new double[]{146.677, 146.615});
        asukohadLaual.put("E", new double[]{301.321, 146.615});
        asukohadLaual.put("F", new double[]{452.939, 146.615});
        asukohadLaual.put("G", new double[]{212.791, 211.739});
        asukohadLaual.put("H", new double[]{301.321, 211.739});
        asukohadLaual.put("I", new double[]{387.155, 212.439});
        asukohadLaual.put("J", new double[]{77.002, 298.691});
        asukohadLaual.put("K", new double[]{146.43, 298.691});
        asukohadLaual.put("L", new double[]{212.791, 298.691});
        asukohadLaual.put("M", new double[]{387.155, 298.691});
        asukohadLaual.put("N", new double[]{453.412, 298.691});
        asukohadLaual.put("O", new double[]{522.676, 298.691});
        asukohadLaual.put("P", new double[]{214.664, 386.43});
        asukohadLaual.put("Q", new double[]{301.321, 386.43});
        asukohadLaual.put("R", new double[]{387.155, 386.43});
        asukohadLaual.put("S", new double[]{146.171, 452.384});
        asukohadLaual.put("T", new double[]{300.739, 454.888});
        asukohadLaual.put("U", new double[]{453.785, 452.936});
        asukohadLaual.put("V", new double[]{77.002, 522.734});
        asukohadLaual.put("W", new double[]{300.971, 523.084});
        asukohadLaual.put("X", new double[]{522.676, 523.424});

        int i = 0;

        for (String s : asukohadLaual.keySet()) {
            Circle c = new Circle(asukohadLaual.get(s)[0], asukohadLaual.get(s)[1], 21.495, Color.WHITE);
            c.setOpacity(0);
            kohad.add(new Koht(s, mängijad.get(2), c, i));
            i++;
        }

        for (Koht koht : kohad) {
            koht.getRing().setOnMouseClicked(event -> {
                täidaKolmikud();
                if (mängufaas == 0) {
                    if (esimeseMängijaKord) {
                        if (järgmineKäikVõtmine1) {
                            koht.setOlek(mängijad.get(2));
                            info.setText("2. mängija, lisa nupp");
                            järgmineKäikVõtmine1 = false;
                        } else if (käikLisabKolmiku(kohad, koht, mängijad.get(0))) {
                            järgmineKäikVõtmine1 = true;
                            esimeseMängijaKord = !esimeseMängijaKord;
                            info.setText("Eemalda nupp 2. mängijalt");
                            koht.setRingLäbipaistvus(true);
                            koht.setOlek(mängijad.get(0));
                            nuppeLisatud += 1;
                        } else {
                            koht.setRingLäbipaistvus(true);
                            koht.setOlek(mängijad.get(0));
                            nuppeLisatud += 1;
                            info.setText("2. mängija, lisa nupp");
                        }
                    } else {
                        if (järgmineKäikVõtmine2) {
                            koht.setOlek(mängijad.get(2));
                            info.setText("1. mängija, lisa nupp");
                            järgmineKäikVõtmine2 = false;
                        } else if (käikLisabKolmiku(kohad, koht, mängijad.get(1))) {
                            järgmineKäikVõtmine2 = true;
                            esimeseMängijaKord = !esimeseMängijaKord;
                            info.setText("Eemalda nupp 1. mängijalt");
                            koht.setRingLäbipaistvus(true);
                            koht.setOlek(mängijad.get(1));
                            nuppeLisatud += 1;
                        } else {
                            koht.setRingLäbipaistvus(true);
                            koht.setOlek(mängijad.get(1));
                            info.setText("1. mängija, lisa nupp");
                            nuppeLisatud += 1;
                        }
                    }
                    esimeseMängijaKord = !esimeseMängijaKord;
                    if (nuppeLisatud >= 18) {
                        info.setText("Liiguta oma nuppe, et saada 3 ritta.");
                        mängufaas = 1;
                    }
                } else if (mängufaas == 1) {
                    if (esimeseMängijaKord) {
                        if (järgmineKäikVõtmine1 && koht.getOlek().equals(mängijad.get(1))) {
                            koht.setOlek(mängijad.get(2));
                            järgmineKäikVõtmine1 = false;
                            info.setText("1. mängija käib");

                        } else if (koht.getOlek().equals(mängijad.get(0))) {
                            for (Koht k2 : kohad) {
                                if (k2.getRingPaint().equals(Color.LIGHTGREEN)) {
                                    k2.setOlek(mängijad.get(2));
                                }
                            }
                            aktiivseNupuKoht = koht;
                            for (Koht k2 : kohad) {
                                if ((new Käik()).võimalikudKäigud(koht.getNimi()).contains(k2.getNimi()) && k2.getOlek().equals(mängijad.get(2))) {
                                    k2.setRingPaint(Color.LIGHTGREEN);
                                    k2.setRingLäbipaistvus(true);
                                }
                            }
                        } else if (koht.getRingPaint().equals(Color.LIGHTGREEN)) {
                            koht.getRing().setFill(mängijad.get(0).getNupuVärv());
                            koht.setOlek(mängijad.get(0));
                            if (käikLisabKolmiku2(kohad, koht, aktiivseNupuKoht, mängijad.get(0))) {
                                info.setText("Saad võtta 2. mängija nupu");
                                järgmineKäikVõtmine1 = true;
                            }

                            aktiivseNupuKoht.setOlek(mängijad.get(2));
                            aktiivseNupuKoht.setRingLäbipaistvus(true);
                            for (Koht k2 : kohad) {
                                if (k2.getRingPaint().equals(Color.LIGHTGREEN)) {
                                    k2.setOlek(mängijad.get(2));
                                    k2.setRingLäbipaistvus(true);
                                }
                            }
                            if (!järgmineKäikVõtmine1) {
                                info.setText("2. mängija käib");
                                esimeseMängijaKord = !esimeseMängijaKord;
                            }


                        }
                    } else {

                        if (järgmineKäikVõtmine2 && koht.getOlek().equals(mängijad.get(0))) {
                            koht.setOlek(mängijad.get(2));
                            järgmineKäikVõtmine2 = false;
                            info.setText("2. mängija käib");
                        } else if (koht.getOlek().equals(mängijad.get(1))) {
                            aktiivseNupuKoht = koht;
                            for (Koht k2 : kohad) {
                                if (k2.getRingPaint().equals(Color.LIGHTGREEN)) {
                                    k2.setOlek(mängijad.get(2));
                                }
                            }
                            for (Koht k2 : kohad) {
                                if ((new Käik()).võimalikudKäigud(koht.getNimi()).contains(k2.getNimi()) && k2.getOlek().equals(mängijad.get(2))) {
                                    k2.setRingPaint(Color.LIGHTGREEN);
                                    k2.setRingLäbipaistvus(true);
                                }
                            }
                        } else if (koht.getRingPaint().equals(Color.LIGHTGREEN)) {
                            koht.getRing().setFill(mängijad.get(1).getNupuVärv());
                            koht.setOlek(mängijad.get(1));
                            if (käikLisabKolmiku2(kohad, koht, aktiivseNupuKoht, mängijad.get(1))) {
                                info.setText("Saad võtta 1. mängija nupu");
                                järgmineKäikVõtmine2 = true;
                            }
                            aktiivseNupuKoht.setOlek(mängijad.get(2));
                            aktiivseNupuKoht.setRingLäbipaistvus(true);
                            for (Koht k2 : kohad) {
                                if (k2.getRingPaint().equals(Color.LIGHTGREEN)) {
                                    k2.setOlek(mängijad.get(2));
                                    k2.setRingLäbipaistvus(true);
                                }
                            }
                            if (!järgmineKäikVõtmine2) {
                                info.setText("1. mängija käib");
                                esimeseMängijaKord = !esimeseMängijaKord;
                            }
                        }
                    }
                    int mängija1Nupud = 0;
                    int mängija2Nupud = 0;
                    for (Koht koht1 : kohad) {
                        if (koht1.getOlek().equals(mängijad.get(0))) mängija1Nupud++;
                        if (koht1.getOlek().equals(mängijad.get(1))) mängija2Nupud++;
                    }
                    if (mängija1Nupud < 3) {
                        info.setText("Mäng sai läbi! Võitis teine mängija");
                    }
                    if (mängija2Nupud < 3) {
                        info.setText("Mäng sai läbi! Võitis esimene mängija");
                    }
                }
                StringBuilder tekst = new StringBuilder();

                for (Koht koht1 : kohad) {
                    if (!koht1.getOlek().equals(mängijad.get(2))){
                        tekst.append(koht1 + " \n");
                    }
                }

                try {
                    kirjutaLogiFaili("Okupeeritud kohad pärast käiku " +käiguNr + ": \n" + tekst);
                } catch (LogiFailiEiLeitudErind logiEiKirjutataErind) {
                    System.out.println("Logifaili ei leitud");
                }
                käiguNr++;
            });
            juur.getChildren().add(koht.getRing());
        }
        return juur;
    }

    private void getButtons(Group juur) {
        Button exit = new Button("EXIT");
        exit.setLayoutX(700);
        exit.setPrefSize(100, 30);
        exit.setLayoutY(200);
        exit.setOnAction(arg0 -> Platform.exit());

        juur.getChildren().addAll(exit);
    }


    @Override
    public void start(Stage peaLava) {
        Stage esimene = new Stage();
        Group alg = new Group();

        Label rida1 = new Label("Tere tulemast mängima lauamängu VESKI!");
        Label rida2 = new Label("   ");
        Label rida3 = new Label("Mäng koosneb kahest faasist:");
        Label rida4 = new Label("1. faasis asetavad mängijad kordamööda lauale 9 nuppu.");
        Label rida5 = new Label("2. faasis liigutavad mängijad mööda jooni oma nuppe.");
        Label rida6 = new Label("   ");
        Label rida7 = new Label("Mängu eesmärk on saada järjest 3 nuppu,");
        Label rida8 = new Label("sest siis saab vastaselt ühe nupu ära võtta");
        Label rida9 = new Label("   ");
        Label rida10 = new Label("Kaotab see, kellel esimesena vähem kui kolm nuppu järele jääb!");
        Label rida11 = new Label("   ");
        Label rida12 = new Label("   ");
        Label rida13 = new Label("Teema muutmiseks vajuta klaviatuuril 'h'-hele või 't'-tume!");
        Label rida14 = new Label("   ");
        Label rida15 = new Label("   ");


        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        List<Label> read = new ArrayList<>(Arrays.asList(rida1, rida2, rida3, rida4, rida5, rida6, rida7, rida8, rida9, rida10, rida11, rida12,rida13,rida14,rida15));

        for (Label l : read) {
            l.setTextFill(Color.WHITE);
            l.setFont(Font.font(15));
        }

        vBox.getChildren().addAll(read);

        Button start = new Button("START");
        start.setPrefSize(100, 30);
        vBox.getChildren().add(start);

        Button exit = new Button("EXIT");
        exit.setPrefSize(100, 30);
        vBox.getChildren().addAll(new Label("  "), exit);


        BorderPane bp = new BorderPane();
        bp.setPrefSize(500, 500);
        bp.setCenter(vBox);
        alg.getChildren().add(bp);


        Scene sissejuhatus = new Scene(alg, 500, 500, taust);
        esimene.setScene(sissejuhatus);
        esimene.setResizable(false);
        esimene.setTitle("Õpetus");
        esimene.show();

        exit.setOnMouseClicked(event -> esimene.hide());

        sissejuhatus.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.H) {
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("hele.txt")))) {
                        setBackground(br.readLine().strip());
                        setMust(br.readLine().strip());
                        setPunane(br.readLine().strip());
                        setTaust(Color.WHITE);
                        sissejuhatus.setFill(taust);
                        for (Label l : read) {
                            l.setTextFill(Color.BLACK);
                            l.setFont(Font.font(15));
                        }
                    } catch (IOException ignored) {
                    }
                } else if (keyEvent.getCode() == KeyCode.T) {
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("tume.txt")))) {
                        setBackground(br.readLine().strip());
                        setMust(br.readLine().strip());
                        setPunane(br.readLine().strip());
                        setTaust(Color.BLACK);
                        sissejuhatus.setFill(taust);
                        for (Label l : read) {
                            l.setTextFill(Color.WHITE);
                            l.setFont(Font.font(15));
                        }
                    } catch (IOException ignored) {
                    }
                }
            }
        });


        start.setOnMouseClicked(event -> {
            try {
                kirjutaLogiFaili("\n UUS MÄNG \n -------------------------------------------- \n");
            } catch (LogiFailiEiLeitudErind logiFailiEiLeitudErind) {

            }
            esimene.hide();

            mängijad.add(new Mängija(punane));
            mängijad.add(new Mängija(must));
            mängijad.add(new Mängija(Color.WHITE));

            Group juur = setUp();
            getButtons(juur);
            Scene stseen1 = new Scene(juur, 900, 600, taust);  // luuakse stseen

            peaLava.setTitle("Meie mäng");  // lava tiitelribale pannakse tekst
            peaLava.setScene(stseen1);  // lavale lisatakse stseen
            peaLava.setResizable(false);
            peaLava.show();  // lava tehakse nähtavaks
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public List<Koht> getKohad() {
        return kohad;
    }
}