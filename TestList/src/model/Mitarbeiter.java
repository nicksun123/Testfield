package model;

public class Mitarbeiter {
    public Mitarbeiter(int id, String name) {
        setId(id);
        setName(name);
    }

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
