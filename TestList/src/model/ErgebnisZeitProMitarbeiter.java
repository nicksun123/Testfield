package model;

public class ErgebnisZeitProMitarbeiter {
    public ErgebnisZeitProMitarbeiter(Mitarbeiter mitarbeiter, long gesamtZeit) {
        setMitarbeiter(mitarbeiter);
        setGesamtZeit(gesamtZeit);
    }

    private Mitarbeiter mitarbeiter;
    private double gesamtZeit;

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
