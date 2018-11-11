package vm.hackatonapp.models;


public class Level {
    public static final int MIN_LEVEL = 7;
    public static final int MAX_LEVEL = 10;

    private int level;
    private String bonusText;
    private float cashback;
    private float deposit;
    private float credit;
    private float lifeInsurance;

    public Level(int level, String bonusText, float cashback, float deposit, float credit, float lifeInsurance) {
        this.level = level;
        this.bonusText = bonusText;
        this.cashback = cashback;
        this.deposit = deposit;
        this.credit = credit;
        this.lifeInsurance = lifeInsurance;
    }

    public Level() {
    }

    public int getLevel() {
        return level;
    }

    public String getBonusText() {
        return bonusText;
    }

    public float getCashback() {
        return cashback;
    }

    public float getDeposit() {
        return deposit;
    }

    public float getCredit() {
        return credit;
    }

    public float getLifeInsurance() {
        return lifeInsurance;
    }
}
