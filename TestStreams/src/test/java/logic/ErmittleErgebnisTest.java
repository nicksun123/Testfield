package logic;

import model.ErgebnisZeitProMitarbeiter;
import model.Mitarbeiter;
import model.Projekt;
import model.Zeiten;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ErmittleErgebnisTest {

    @Spy
    ErmittleErgebnis ermittleErgebnis;

    @Mock
    List<String> testList;

    @org.junit.jupiter.api.Test
    void TEST_testlist_WHEN_mocked() {
        //prepare
        when(testList.get(anyInt())).thenReturn("test text");
        //execute

        //assert
        assertEquals("test text", testList.get(0));
    }

    @org.junit.jupiter.api.Test
    void TEST_ermittleZeit_WITH_valid_data_EXPECT_correct_output() {
        //prepare
        //execute
        List<ErgebnisZeitProMitarbeiter> zusammenfassung = ermittleErgebnis.ermittleZeit(
                datenErstellen(), YearMonth.of(2023, Month.MARCH));
        ermittleErgebnis.ermittleZeit(
                datenErstellen(), YearMonth.of(2023, Month.MARCH));

        //assert
//        verify(ermittleErgebnis).ermittleZeit(any(), any());
        verify(ermittleErgebnis, times(2)).ermittleZeit(anyList(), eq(YearMonth.of(2023, Month.MARCH)));
        verify(ermittleErgebnis, times(2)).ermittleZeit(any(), any());
        verify(ermittleErgebnis, atLeast(1)).ermittleZeit(any(), any());
        verify(ermittleErgebnis, atLeastOnce()).ermittleZeit(any(), any());
        verify(ermittleErgebnis, atMost(5)).ermittleZeit(any(), any());
//        verify(ermittleErgebnis, never()).ermittleZeit(any(), any());
        assertEquals(5, zusammenfassung.size());
    }

    @org.junit.jupiter.api.Test
    void TEST_convertToLocalDateTime_WITH_valid_date_EXPECT_correct_output() {
        //prepare
        LocalDateTime expectedTime = LocalDateTime.of(2023, Month.MARCH, 1, 10, 0, 0); ;
        //execute
        LocalDateTime time = ermittleErgebnis.convertToLocalDateTime(Timestamp.valueOf("2023-03-01 10:00:00.0"));
        //assert
        assertTrue(time.equals(expectedTime));
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