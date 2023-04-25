package logic;

import model.ErgebnisZeitProMitarbeiter;
import model.Mitarbeiter;
import model.Projekt;
import model.Zeiten;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import java.util.stream.Collectors.*;

import static java.util.stream.Collectors.*;

public class ErmittleErgebnis {
    public List<ErgebnisZeitProMitarbeiter> ermittleZeit(List<Zeiten> zeiten, YearMonth yearMonth){
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
                .toList();

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

    public LocalDateTime convertToLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }
}