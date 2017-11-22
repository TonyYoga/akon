package ru.job4j;

import java.util.ArrayList;

public class Undead extends Generation {

    public Undead() {
        this.setgName("Нежить");
        ArrayList<Creature> unit = new ArrayList<>();
        unit.add(new Necromant());
        unit.add(new Hunter());
        unit.add(new Hunter());
        unit.add(new Hunter());
        unit.add(new Zombi());
        unit.add(new Zombi());
        unit.add(new Zombi());
        unit.add(new Zombi());
        this.unit = unit;
        FantasyGame.printLog("Создан отряд нежети");

    }
    public class Necromant extends Creature implements Mag {
            double defDamage = 5;
            public Necromant() {
                this.setName("Некромант");
                this.setHp(100);
                this.setPremium(false);
                this.setDoStep(false);
                this.setAvalible();
                this.setDamage(defDamage);
                this.setRace(Race.UNDEAD);
            }

        @Override
        public boolean spel(Creature c) {
            c.setDamage(c.getDamage() * 0.5);
            FantasyGame.printLog(this.getName() + " накладывает проклятие на " + c.getName());
            //setPremium(false);
            return true;
        }
        @Override
        public boolean turn(Creature c) {
            boolean rez = false;
            if (isPremium) {
                setDamage(getDamage() * 1.5);
            }
            int ran = (int) (Math.random() * 2);
            if (ran == 0) {
                rez = spel(c);
            } else {
                rez = attack(c);
            }
            setPremium(false);
            setDamage(defDamage);
            setDoStep(true);
            return rez;
        }

    }
    public class Hunter extends Creature implements Archer {
        double defDamage = 2;
        double defShotDamage = 4; //default value
        double shotDamage;
            public Hunter() {
                this.setName("Охотник");
                this.setHp(100);
                this.setPremium(false);
                this.setDoStep(false);
                this.setAvalible();
                this.setDamage(defDamage);
                this.setShotDamage(defShotDamage);
                this.setRace(Race.UNDEAD);
            }

        @Override
        public boolean shot(Creature c) {
            c.setHp(c.getHp() - this.getShotDamage());
            FantasyGame.printLog(this.getName() + " выпускает стрелу и наносит ущерб " + this.shotDamage + " " + c.getName() + "(" + c.getHp() + ")");
            c.setAvalible();
            //setPremium(false);
            return true;

        }
        @Override
        public boolean turn(Creature c) {
            if (isPremium) {
                setDamage(getDamage() * 1.5);
                setShotDamage(getShotDamage() * 1.5);
            }
            int ran = (int) (Math.random() * 2);
            if (ran == 0) {
                shot(c);
            } else {
                attack(c);
            }
            setPremium(false);
            setDamage(defDamage);
            setShotDamage(defShotDamage);
            setDoStep(true);
            return true;
        }
        public double getShotDamage() {
            return shotDamage;
        }

        public void setShotDamage(double shotDamage) {

            this.shotDamage = shotDamage;
        }

    }
    public class Zombi extends Creature implements Warrior {
        double defDamage = 15;
            public Zombi() {
                this.setName("Зомби");
                this.setHp(100);
                this.setPremium(false);
                this.setDoStep(false);
                this.setAvalible();
                this.setDamage(defDamage);
                this.setRace(Race.UNDEAD);
            }
        @Override
        public boolean turn(Creature c) {
            if (isPremium) {
                setDamage(getDamage() * 1.5);
            }
            attack(c);
            setPremium(false);
            setDamage(defDamage);
            setDoStep(true);
            return true;
        }


    }
}

