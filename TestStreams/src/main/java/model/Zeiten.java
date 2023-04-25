package model;

import java.sql.Timestamp;

public class Zeiten {
    public Zeiten(int id, String beschreibung, Timestamp  start, Timestamp  stopp,
                  Projekt projekt, Mitarbeiter mitarbeiter) {
        setId(id);
        setBeschreibung(beschreibung);
        setStart(start);
        setStopp(stopp);
        setProjekt(projekt);
        setMitarbeiter(mitarbeiter);
    }

    private Projekt projekt;
    private Mitarbeiter mitarbeiter;
    private int id;
    private String beschreibung;

    private Timestamp  start;
    private Timestamp  stopp;

    public Projekt getProjekt() {
        return projekt;
    }

    public void setProjekt(Projekt projekt) {
        this.projekt = projekt;
    }

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp  start) {
        this.start = start;
    }

    public Timestamp  getStopp() {
        return stopp;
    }

    public void setStopp(Timestamp  stopp) {
        this.stopp = stopp;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getHashKey(){
        return getMitarbeiter().getId() + "_" + getProjekt().getId();
    }

    public long gesamtZeit(){
        return (getStopp().getTime() - getStart().getTime()) / 1000 / 60 / 60;
    }
}
