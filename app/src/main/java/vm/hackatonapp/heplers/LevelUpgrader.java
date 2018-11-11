package vm.hackatonapp.heplers;

import vm.hackatonapp.models.Level;
import vm.hackatonapp.models.UserBonus;


public class LevelUpgrader {
    public void upgrade(Level level, UserBonus statistic) {
        statistic.setCurrentLevel(level.getLevel());
        statistic.addCashback(level.getCashback());
        statistic.addDeposit(level.getDeposit());
        statistic.addLifeInsurance(level.getLifeInsurance());
        statistic.substractCredit(level.getCredit());
    }
}
