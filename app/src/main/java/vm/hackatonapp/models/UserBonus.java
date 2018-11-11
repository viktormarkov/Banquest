package vm.hackatonapp.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity
public class UserBonus {

    @PrimaryKey
    private long id = 1;
    private int currentLevel;
    private float cashback;
    private float deposit;
    private float credit;
    private float lifeInsurance;

    public UserBonus(int currentLevel, float cashback, float deposit, float credit, float lifeInsurance) {
        this.currentLevel = currentLevel;
        this.cashback = cashback;
        this.deposit = deposit;
        this.credit = credit;
        this.lifeInsurance = lifeInsurance;
    }

    public UserBonus() {
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCashback(float cashback) {
        this.cashback = cashback;
    }

    public void addCashback(float cashback) {
        this.cashback += cashback;
    }

    public float getCashback() {
        return cashback;
    }

    public void setDeposit(float deposit) {
        this.deposit = deposit;
    }

    public void addDeposit(float deposit) {
        this.deposit += deposit;
    }

    public float getDeposit() {
        return deposit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public void substractCredit(float credit) {
        this.credit -= credit;
    }

    public float getCredit() {
        return credit;
    }

    public void setLifeInsurance(float lifeInsurance) {
        this.lifeInsurance = lifeInsurance;
    }

    public void addLifeInsurance(float lifeInsurance) {
        this.lifeInsurance += lifeInsurance;
    }

    public float getLifeInsurance() {
        return lifeInsurance;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
