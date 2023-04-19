package model;

import java.util.HashMap;
import java.util.Map;

public class ErgebnisMap<Mitarbeiter, Projekt, Double> {
    private final Map<Mitarbeiter, Double> mitarbeiterMap = new HashMap<>();
    private final Map<Projekt, Double> projektMap = new HashMap<>();

    public void put(Mitarbeiter mitarbeiterId, Projekt projektId, Double value) {
        if (mitarbeiterId != null) {
            mitarbeiterMap.put(mitarbeiterId, value);
        }
        if (projektId != null) {
            projektMap.put(projektId, value);
        }
    }

    public Double get(Mitarbeiter mitarbeiterId, Projekt projektId) {
        if (mitarbeiterMap.containsKey(mitarbeiterId) && projektMap.containsKey(projektId)) {
            if (mitarbeiterMap.get(mitarbeiterId).equals(projektMap.get(projektId))) {
                return projektMap.get(projektId);
            }
        }
        return null;
    }

    public Double getByMitarbeiterId(Mitarbeiter mitarbeiterId) {
        return get(mitarbeiterId, null);
    }

    public Double getByOtherKey(Projekt projektId) {
        return get(null, projektId);
    }
}
