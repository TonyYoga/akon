package fantasygame;

import java.util.ArrayList;

public class People extends Generation {

    public People() {
        this.setgName("Люди");
        ArrayList<Creature> unit = new ArrayList<>();
        unit.add(new MagHuman());
        unit.add(new Arbalet());
        unit.add(new Arbalet());
        unit.add(new Arbalet());
        unit.add(new Warrior());
        unit.add(new Warrior());
        unit.add(new Warrior());
        unit.add(new Warrior());
        this.unit = unit;
        FantasyGame.printLog("Создан отряд людей");

    }

    public class MagHuman extends Creature implements Mag {
        double defDamage = 4;
        public MagHuman() {
            this.setName("Магчел");
            this.setHP(100);
            this.setPremium(false);
            this.setDoStep(false);
            this.setAvalible();
            this.setDamage(defDamage);
            this.setRace(Race.PEOPLE);
        }

        @Override
        public boolean spel(Creature c) {
            c.setPremium(true);
            FantasyGame.printLog(this.getName() + " накладывает улучшение на " + c.getName());
            //setPremium(false);
            return true;
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
    }
    public class Arbalet extends Creature implements Archer {
        double defDamage = 3;
        double defShotDamage = 5; //default value
        double shotDamage;
        public Arbalet() {
            this.setName("Арбалетчик");
            this.setHP(100);
            this.setPremium(false);
            this.setDoStep(false);
            this.setAvalible();
            this.setDamage(defDamage);
            this.setShotDamage(defShotDamage);
            this.setRace(Race.PEOPLE);
        }

        @Override
        public boolean shot(Creature c) {

            c.setHP(c.getHP() - this.getShotDamage());
            FantasyGame.printLog(this.getName() + " выпускает стрелу и наносит ущерб " + this.shotDamage + " " + c.getName() + "(" + c.getHP() + ")");
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
                shot(c)
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
    public class Warrior extends Creature implements Warrior {
        double defDamage = 15;
        public Warrior() {
            this.setName("Рыцарь");
            this.setHP(100);
            this.setPremium(false);
            this.setDoStep(false);
            this.setAvalible();
            this.setDamage(defDamage);
            this.setRace(Race.PEOPLE);
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
