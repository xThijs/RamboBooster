package nl.canify.rambobooster.boosters;

import java.util.ArrayList;
import java.util.List;

public class BoosterManager {

    private List<Booster> boosters;

    public BoosterManager() {
        this.boosters = new ArrayList<>();
    }

    public boolean isActiveBooster() {
        return boosters.size() > 0;
    }

    public void addBooster(Booster booster) {
        boosters.add(booster);
    }

    public void removeBooster(Booster booster) {
        booster.cancelTask();
        boosters.remove(booster);
    }

    public Booster getActiveBooster() {
        return boosters.get(0);
    }

}
