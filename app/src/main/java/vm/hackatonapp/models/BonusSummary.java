package vm.hackatonapp.models;

import java.util.Map;


public class BonusSummary {
    private Map<Integer, Level> levels;
    private UserBonus userBonus;

    public BonusSummary(Map<Integer, Level> levels, UserBonus userBonus) {
        this.levels = levels;
        this.userBonus = userBonus;
    }

    public Map<Integer, Level> getLevels() {
        return levels;
    }

    public UserBonus getUserBonus() {
        return userBonus;
    }
}
