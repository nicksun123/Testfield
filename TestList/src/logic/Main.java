package logic;

import model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Ergebnis> ergebnisse = werteAuflisten(datenErstellen());
        ergebnisse.forEach(ergebnis -> System.out.printf(
                "Mitarbeiter %s arbeitete im Projekt %s insgesamt %.2f Stunden.%n",
                ergebnis.getMitarbeiter().getName(), ergebnis.getProjekt().getName(), ergebnis.getGesamtZeit()));
    }

    public static List<Ergebnis> werteAuflisten(List<Zeiten> zeiten){
        List<Ergebnis> ergebnisse = new ArrayList();

        zeiten.stream().forEach(zeit -> {
            int currentMitId = zeit.getMitarbeiter().getId();
            int currentProjId = zeit.getProjekt().getId();
            double gesamtZeit = gesamtZeitErrechnen(zeit.getStart(), zeit.getStopp());
            boolean ergebnisErstellt = false;

            for (Ergebnis ergebnis : ergebnisse) {
                if (ergebnis != null) {
                    if (ergebnis.getMitarbeiter().getId() == currentMitId
                            && ergebnis.getProjekt().getId() == currentProjId) {
                        ergebnis.setGesamtZeit(gesamtZeit);
                        ergebnisErstellt = true;
                    }
                }
            }

            if (!ergebnisErstellt){
                ergebnisse.add(new Ergebnis(zeit.getProjekt(),  zeit.getMitarbeiter(), gesamtZeit));
            }
        });
        return ergebnisse;
    }

    public static double gesamtZeitErrechnen(Date start, Date stopp){
        final int MILLI_TO_HOUR = 1000 * 60 * 60;
        long diff = (stopp.getTime() - start.getTime());
        return (double) diff / MILLI_TO_HOUR;
    }

    public static List<Zeiten> datenErstellen(){
        List<Zeiten> erstellteZeiten = new ArrayList();

        Mitarbeiter nico = new Mitarbeiter(1, "Nico");
        Mitarbeiter chabo = new Mitarbeiter(2, "Chabo");
        Mitarbeiter babo = new Mitarbeiter(3, "Babo");

        Projekt neom = new Projekt(1, "NEOM");
        Projekt autoGmbh = new Projekt(2, "Auto GmbH");
        Projekt metallGmbh = new Projekt(3, "Metall GmbH");

        erstellteZeiten.add(new Zeiten(1,"", zeitstempelErstellen("2023-03-01 10:00:00"),
                zeitstempelErstellen("2023-03-01 16:00:00"), neom, nico));
        erstellteZeiten.add(new Zeiten(2,"", zeitstempelErstellen("2023-03-02 10:00:00"),
                zeitstempelErstellen("2023-03-02 13:00:00"), neom, nico));
        erstellteZeiten.add(new Zeiten(3,"", zeitstempelErstellen("2023-03-03 09:00:00"),
                zeitstempelErstellen("2023-03-03 17:00:00"), neom, nico));

        erstellteZeiten.add(new Zeiten(4,"", zeitstempelErstellen("2023-03-01 10:00:00"),
                zeitstempelErstellen("2023-03-01 11:00:00"), autoGmbh, chabo));
        erstellteZeiten.add(new Zeiten(5,"", zeitstempelErstellen("2023-03-02 10:00:00"),
                zeitstempelErstellen("2023-03-02 18:30:00"), autoGmbh, chabo));
        erstellteZeiten.add(new Zeiten(6,"", zeitstempelErstellen("2023-03-03 09:00:00"),
                zeitstempelErstellen("2023-03-03 17:45:00"), autoGmbh, chabo));

        erstellteZeiten.add(new Zeiten(7,"", zeitstempelErstellen("2023-03-01 10:00:00"),
                zeitstempelErstellen("2023-03-01 18:00:00"), metallGmbh, babo));
        erstellteZeiten.add(new Zeiten(8,"", zeitstempelErstellen("2023-03-02 10:00:00"),
                zeitstempelErstellen("2023-03-02 16:00:00"), metallGmbh, babo));
        erstellteZeiten.add(new Zeiten(9,"", zeitstempelErstellen("2023-03-03 09:00:00"),
                zeitstempelErstellen("2023-03-03 17:00:00"), metallGmbh, babo));

        erstellteZeiten.add(new Zeiten(1,"", zeitstempelErstellen("2023-03-01 10:00:00"),
                zeitstempelErstellen("2023-03-01 18:00:00"), autoGmbh, nico));

        erstellteZeiten.add(new Zeiten(1,"", zeitstempelErstellen("2023-03-01 10:00:00"),
                zeitstempelErstellen("2023-03-01 15:30:00"), metallGmbh, chabo));

        erstellteZeiten.add(new Zeiten(1,"", zeitstempelErstellen("2023-03-01 10:00:00"),
                zeitstempelErstellen("2023-03-01 19:30:00"), autoGmbh, babo));

        return erstellteZeiten;
    }

    public static Date zeitstempelErstellen(String date){
        try {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}