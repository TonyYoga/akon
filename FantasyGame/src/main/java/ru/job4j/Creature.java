package ru.job4j;

public class Creature {
    String name;
    double hp;
    protected boolean isPremium;
    boolean doStep;
    boolean isAvalible;
    double damage;
    //double shot_damage;
    Race race;
    enum  Race { ELF, ORKS, PEOPLE, UNDEAD };

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setAvalible() {
        if (hp > 0) {
            isAvalible = true;
        } else {
            isAvalible = false;
            FantasyGame.printLog(this.name + " убит!");
        }

    }

    public boolean isAvalible() {
        return isAvalible;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public void setDoStep(boolean doStep) {
        this.doStep = doStep;
    }

    public String getName() {
        return name;
    }

    public double getHp() {
        return hp;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public boolean isDoStep() {
        return doStep;
    }

    //Action methods

    public boolean attack(Creature c) {
        c.setHp(c.getHp() - this.getDamage());
        FantasyGame.printLog(this.getName() + " наносит удар силой " + this.getDamage() + " по " + c.getName() + "(" + c.getHp() + ")");
        c.setAvalible();
        //this.setPremium(false);
        return true;
    }
    public boolean turn(Creature c) {
        FantasyGame.printLog("пустой ход :( не должно быть видно");
        return false;
    }
}
