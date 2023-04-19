package model;

public class Ergebnis {
    public Ergebnis(Projekt projekt, Mitarbeiter mitarbeiter, double gesamtZeit) {
        setProjekt(projekt);
        setMitarbeiter(mitarbeiter);
        setGesamtZeit(gesamtZeit);
    }

    private Projekt projekt;
    private Mitarbeiter mitarbeiter;
    private double gesamtZeit;

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

    public double getGesamtZeit() {
        return gesamtZeit;
    }

    public void setGesamtZeit(double gesamtZeit) {
        this.gesamtZeit += gesamtZeit;
    }
}
