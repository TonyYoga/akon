package ru.job4j;

import java.util.ArrayList;

public class Orks extends Generation {

        public Orks() {
            this.setgName("Орки");
            ArrayList<Creature> unit = new ArrayList<>();
            unit.add(new Shaman());
            unit.add(new ArcherOrk());
            unit.add(new ArcherOrk());
            unit.add(new ArcherOrk());
            unit.add(new Goblin());
            unit.add(new Goblin());
            unit.add(new Goblin());
            unit.add(new Goblin());
            this.unit = unit;
            FantasyGame.printLog("Создан отряд орков");

        }

        public class Shaman extends Creature implements Mag {
            public Shaman() {

                this.setName("Шаман");
                this.setHp(100);
                this.setPremium(false);
                this.setDoStep(false);
                this.setAvalible();
                this.setRace(Race.ORKS);

            }

            @Override
            public boolean spel(Creature c) {
                if (c.race.equals(Race.PEOPLE) || c.race.equals(Race.ELF)) {
                    c.setPremium(false);
                    FantasyGame.printLog(this.getName() + " снимает улучшение с " + c.getName());
                    //FantasyGame.printLog(Strinthis.getName() + " снимает улучшение с " + c.getName());
                } else if (c.race.equals(Race.ORKS) || c.race.equals(Race.UNDEAD)) {
                    c.setPremium(true);
                    FantasyGame.printLog(this.getName() + " накладывает улучшение на " + c.getName());
                }
                //setPremium(false);
                return true;
            }
            @Override
            public boolean turn(Creature c) {
                boolean rez = false;
                rez = spel(c);
                setPremium(false);
                setDoStep(true);
                return rez;
            }

        }
        public class ArcherOrk extends Creature implements Archer {
            double defDamage = 2;
            double defShotDamage = 3; //default value
            double shotDamage;

            public ArcherOrk() {
                this.setName("Лучник орк");
                this.setHp(100);
                this.setPremium(false);
                this.setDoStep(false);
                this.setAvalible();
                this.setDamage(defDamage);
                this.setShotDamage(defShotDamage);
                this.setRace(Race.ORKS);
            }

            @Override
            public boolean shot(Creature c) {
                c.setHp(c.getHp() - this.getShotDamage());
                //FantasyGame.printLog(this.getName() + " выпускает стрелу и наносит ущерб " + this.shotDamage + " " + c.getName() + "(" + c.getHp() + ")");
                FantasyGame.printLog(String.format("%s выпускает стрелу и наносит ущерб %f по %s (%f)", this.getName(), this.shotDamage, c.getName(), c.getHp()));
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
        public class Goblin extends Creature implements Warrior {
            double defDamage = 20;

            public Goblin() {
                this.setName("Гоблин");
                this.setHp(100);
                this.setPremium(false);
                this.setDoStep(false);
                this.setAvalible();
                this.setDamage(defDamage);
                this.setRace(Race.ORKS);
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


