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

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class ErmittleErgebnis {
    public List<ErgebnisZeitProMitarbeiter> ermittleZeit(List<Zeiten> zeiten, YearMonth yearMonth){
        List<ErgebnisZeitProMitarbeiter> zusammenfassung = zeiten.stream()
                .filter(zeit -> convertToLocalDateTime(zeit.getStart()).getMonthValue() == yearMonth.getMonthValue()
                        && convertToLocalDateTime(zeit.getStart()).getYear() == yearMonth.getYear()) //Nach gewuenschten Monat filtern
                .collect(groupingBy(Zeiten::getHashKey, toSet()))//Nach Hash Key buendeln
                .entrySet().stream()
                .map(entry -> entry.getValue()) //Stream aus Set<Zeiten> erstellen
                .filter(set -> set.stream().mapToLong(setElement -> setElement.gesamtZeit()).sum() >= 3) //Summe groesser 3 ermitteln
                .map( entry->
                        new ErgebnisZeitProMitarbeiter(entry.stream().findFirst().get().getMitarbeiter(),
                                entry.stream().findFirst().get().getProjekt(),
                                entry.stream().map(Zeiten::gesamtZeit).reduce(Long::sum).get())
                ).collect(toList());

        return zusammenfassung;
    }

    public LocalDateTime convertToLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }
}

//    Der Buchhalter eines Consultingunternehmen, will Rechnungen für den vergangenen Monat schreiben.
//        Schreibe ein SQL was beispielhaft die folgende Ausgabe haben könnte:
//        Timo 10 Stunden für Deutsche Bank
//        Timo 30 Stunden für VW
//        Maximilian 50 Stunden für Rhenag gearbeitet
//        Es sollen nur Zeilen generiert werden pro Mitarbeiter und Kunde, bei dem mindestens 3 Stunden erreicht werden.
