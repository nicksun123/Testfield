package logic;

import model.Mitarbeiter;
import model.Projekt;
import model.Zeiten;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ErmittleErgebnisTest {

    @org.junit.jupiter.api.Test
    void ermittleZeit() {
    }

    @org.junit.jupiter.api.Test
    void convertToLocalDateTime() {
    }

    List<Zeiten> datenErstellen() {
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