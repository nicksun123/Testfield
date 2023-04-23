package model;

import java.util.Date;

public class ZeitenOld {
    public ZeitenOld(int id, String beschreibung, Date start, Date stopp,
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

    private Date start;
    private Date stopp;

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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getStopp() {
        return stopp;
    }

    public void setStopp(Date stopp) {
        this.stopp = stopp;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
