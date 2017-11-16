package fantasygame;

import java.util.ArrayList;

public class Elf extends Generation {

    public Elf() {
        this.setgName("Ельфы");
        ArrayList<Creature> unit = new ArrayList<>();
        unit.add(new MagElf());
        unit.add(new Archer());
        unit.add(new Archer());
        unit.add(new Archer());
        unit.add(new Warrior());
        unit.add(new Warrior());
        unit.add(new Warrior());
        unit.add(new Warrior());
        this.unit = unit;
        FantasyGame.printLog("Создан отряд эьфов");

    }

    public class MagElf extends Creature implements Mag {
        double defDamage = 10;


        public MagElf() {

            this.setName("Маг");
            this.setHP(100);
            this.setPremium(false);
            this.setDoStep(false);
            this.setAvalible();
            this.setDamage(defDamage);
            this.setRace(Race.ELF);
        }

        @Override
        public boolean turn(Creature c) {
            boolean rez = false;
            if (isPremium) {
                setDamage(getDamage() * 1.5);
            }
            if (c.race.equals(Creature.Race.ORKS) || c.race.equals(Race.UNDEAD)) {
                rez = attack(c);
            } else if (c.race.equals(Race.ELF) || c.race.equals(Race.PEOPLE)) {
                rez = spel(c);
            }
            setPremium(false);
            setDamage(defDamage);
            setDoStep(true);
            return rez;
        }

        @Override
        public boolean spel(Creature c) {
            c.setPremium(true);
            FantasyGame.printLog(this.getName() + " накладывает улучшение на " + c.getName());
            //setPremium(false);
            return true;
        }

    }
    public class Archer extends Creature implements Archer {

        double defDamage = 3;
        double defShotDamage = 7; //default value
        double shotDamage;
        public Archer() {
            this.setName("Лучник эльф");
            this.setHP(100);
            this.setPremium(false);
            this.setDoStep(false);
            this.setAvalible();
            this.setDamage(defDamage);
            this.setShotDamage(defShotDamage);
            this.setRace(Race.ELF);
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

        @Override
        public boolean shot(Creature c) {
            c.setHP(c.getHP() - this.getShotDamage());
            FantasyGame.printLog(this.getName() + " выпускает стрелу и наносит ущерб " + this.shotDamage + " " + c.getName() + "(" + c.getHP() + ")");

            c.setAvalible();
            //setPremium(false);
            return true;
        }

    }
    public class Warrior extends Creature implements Warrior {

        double defDamage = 15;
        public Warrior() {
            this.setName("Воин");
            this.setHP(100);
            this.setPremium(false);
            this.setDoStep(false);
            this.setAvalible();
            this.setDamage(defDamage);
            this.setRace(Race.ELF);

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
