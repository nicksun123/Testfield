package model;

public class ErgebnisZeitProMitarbeiter {
    public ErgebnisZeitProMitarbeiter(Mitarbeiter mitarbeiter, Projekt projekt, long gesamtZeit) {
        setMitarbeiter(mitarbeiter);
        setProjekt(projekt);
        setGesamtZeit(gesamtZeit);
    }

    private Mitarbeiter mitarbeiter;
    private Projekt projekt;
    private double gesamtZeit;

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public Projekt getProjekt() {
        return projekt;
    }

    public void setProjekt(Projekt projekt) {
        this.projekt = projekt;
    }

    public double getGesamtZeit() {
        return gesamtZeit;
    }

    public void setGesamtZeit(double gesamtZeit) {
        this.gesamtZeit += gesamtZeit;
    }
}
