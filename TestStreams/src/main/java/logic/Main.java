package logic;

import model.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.*;

public class Main {
    public static void main(String[] args) {
        List<ErgebnisZeitProMitarbeiter> ergebnisse = ermittleZeit(datenErstellen(), YearMonth.of(2023, Month.MARCH));

        ergebnisse.forEach(ergebnis -> System.out.printf(
                "Mitarbeiter %s arbeitete im Projekt %s insgesamt %.2f Stunden.%n",
                ergebnis.getMitarbeiter().getName(), ergebnis.getProjekt().getName(), ergebnis.getGesamtZeit()));
    }

    public static List<ErgebnisZeitProMitarbeiter> ermittleZeit(List<Zeiten> zeiten, YearMonth yearMonth){
        List<ErgebnisZeitProMitarbeiter> ergebnisse = new ArrayList<>();
//        int adjustMonthBase = 1;
//        int adjustYearBase = 1990;

        //Summe der Sets ermitteln und gegen >= 3 Stunden pruefen
        List<Set<Zeiten>> zusammenfassung = zeiten.stream()
                .filter(zeit -> convertToLocalDateTime(zeit.getStart()).getMonthValue() == yearMonth.getMonthValue()
                        && convertToLocalDateTime(zeit.getStart()).getYear() == yearMonth.getYear()) //Nach gewuenschten Monat filtern
                .collect(groupingBy(Zeiten::getHashKey, toSet()))//Nach Hash Key buendeln
                .entrySet().stream()
                .map(entry -> entry.getValue()) //Stream aus Set<Zeiten> erstellen
                .filter(set -> set.stream().mapToLong(setElement -> setElement.gesamtZeit()).sum() >= 3) //Summe groesser 3 ermitteln
                .collect(toList());

        //System.out.println(zusammenfassung);

        zusammenfassung.forEach(zeitenSet -> {
            Mitarbeiter currentMitarbeiter = null;
            Projekt currentProjekt = null;
            long gesamtZeit = 0;

            for (Zeiten zeit : zeitenSet) {
                currentMitarbeiter = zeit.getMitarbeiter();
                currentProjekt = zeit.getProjekt();
                gesamtZeit += zeit.gesamtZeit();
            }
            ergebnisse.add(new ErgebnisZeitProMitarbeiter(currentMitarbeiter, currentProjekt, gesamtZeit));
        });

        return ergebnisse;
    }

    public static LocalDateTime convertToLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    public static List<Zeiten> datenErstellen(){
        List<Zeiten> erstellteZeiten = new ArrayList();

        Mitarbeiter nico = new Mitarbeiter(1, "Nico");
        Mitarbeiter chabo = new Mitarbeiter(2, "Chabo");
        Mitarbeiter babo = new Mitarbeiter(3, "Babo");

        Projekt neom = new Projekt(1, "NEOM");
        Projekt autoGmbh = new Projekt(2, "Auto GmbH");
        Projekt metallGmbh = new Projekt(3, "Metall GmbH");

        erstellteZeiten.add(new Zeiten(1,"", Timestamp.valueOf("2023-03-01 10:00:00.0"),
                Timestamp.valueOf("2023-03-01 16:00:00.0"), neom, nico));
        erstellteZeiten.add(new Zeiten(2,"", Timestamp.valueOf("2023-03-02 10:00:00.0"),
                Timestamp.valueOf("2023-03-02 13:00:00.0"), neom, nico));
        erstellteZeiten.add(new Zeiten(3,"", Timestamp.valueOf("2023-03-03 09:00:00.0"),
                Timestamp.valueOf("2023-03-03 17:00:00.0"), neom, nico));

        erstellteZeiten.add(new Zeiten(4,"", Timestamp.valueOf("2023-03-01 10:00:00.0"),
                Timestamp.valueOf("2023-03-01 11:00:00.0"), autoGmbh, chabo));
        erstellteZeiten.add(new Zeiten(5,"", Timestamp.valueOf("2023-03-02 10:00:00.0"),
                Timestamp.valueOf("2023-03-02 18:30:00.0"), autoGmbh, chabo));
        erstellteZeiten.add(new Zeiten(6,"", Timestamp.valueOf("2023-03-03 09:00:00.0"),
                Timestamp.valueOf("2023-03-03 17:45:00.0"), autoGmbh, chabo));

        erstellteZeiten.add(new Zeiten(7,"", Timestamp.valueOf("2023-03-01 10:00:00.0"),
                Timestamp.valueOf("2023-03-01 18:00:00.0"), metallGmbh, babo));
        erstellteZeiten.add(new Zeiten(8,"", Timestamp.valueOf("2023-03-02 10:00:00.0"),
                Timestamp.valueOf("2023-03-02 16:00:00.0"), metallGmbh, babo));
        erstellteZeiten.add(new Zeiten(9,"", Timestamp.valueOf("2023-03-03 09:00:00.0"),
                Timestamp.valueOf("2023-03-03 17:00:00.0"), metallGmbh, babo));

        erstellteZeiten.add(new Zeiten(10,"", Timestamp.valueOf("2023-03-01 10:00:00.0"),
                Timestamp.valueOf("2023-03-01 18:00:00.0"), autoGmbh, nico));

        erstellteZeiten.add(new Zeiten(11,"", Timestamp.valueOf("2023-03-01 10:00:00.0"),
                Timestamp.valueOf("2023-03-01 15:30:00.0"), metallGmbh, chabo));

        erstellteZeiten.add(new Zeiten(12,"", Timestamp.valueOf("2023-03-01 10:00:00.0"),
                Timestamp.valueOf("2023-03-01 11:30:00.0"), autoGmbh, babo));

        return erstellteZeiten;
    }
}