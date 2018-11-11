package vm.hackatonapp.data;

import java.util.HashMap;
import java.util.Map;

import vm.hackatonapp.models.Level;


public class LevelStorage {
    private Map<Integer, Level> levels = new HashMap<>();

    public LevelStorage() {
        levels.put(10, new Level(10, "You earned -1% for credit!",0f, 0f, 1f, 0f));
        levels.put(9, new Level(9, "+20% discount for life insurance",0f, 0f, 0f, 20f));
        levels.put(8, new Level(8, "You earned +1% Cashback!",1f, 0f, 0f, 0f));
        levels.put(7, new Level(7, "You earned +1% for deposit!",0f, 1f, 0f, 0f));
        levels.put(6, new Level(6, "You earned -1% for credit!",0f, 0f, 1f, 0f));
        levels.put(5, new Level(5, "You earned +0.5% Cashback!",0.5f, 0f, 0f, 0f));
        levels.put(4, new Level(4, "+5% discount for life insurance",0f, 0f, 0f, 5f));
        levels.put(3, new Level(3, "You earned +0.5% for deposit!",0f, 0.5f, 0f, 0f));
        levels.put(2, new Level(2, "You earned -0.5% for credit!", 0f, 0f, 0.5f, 0f));
        levels.put(1, new Level(1, "You are amazing :)", 0f, 0f, 0f, 0f));

    }

    public Map<Integer, Level> getLevels() {
        return levels;
    }
}
