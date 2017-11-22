package ru.job4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Random;

/**
 * fantasy game - консольная автоматическая фентезийная игра
 */

public class FantasyGame {
    private boolean isGameOver;
    public static StringBuilder stringBuilder = new StringBuilder();
    public static FantasyGame game;
    public Generation com1, com2;
    Random random = new Random();

    public FantasyGame() {
        Random random = new Random();
        int gen = random.nextInt(4);
        int rcom = random.nextInt(2);
        //проверка на Людях и орках
//        com1 = new People(); //testing row
//        com2 = new Orks();//testing row
        //System.out.println(rcom);
        switch (gen) {
            //Random creation of commamds
            case 0:
                if (rcom == 0) {
                    com1 = new Elf();
                    com2 = new Orks();
                } else {
                    com1 = new Orks();
                    com2 = new Elf();
                }
                break;
            case 1:
                if (rcom == 0) {
                    com1 = new Elf();
                    com2 = new Undead();
                } else {
                    com1 = new Undead();
                    com2 = new Elf();
                }
                break;
            case 2:
                if (rcom == 0) {
                    com1 = new People();
                    com2 = new Orks();
                } else {
                    com1 = new Orks();
                    com2 = new People();

                }
                break;
            case 3:
                if (rcom == 0) {
                    com1 = new People();
                    com2 = new Undead();
                } else {
                    com1 = new Undead();
                    com2 = new People();
                }

                break;
            default:
                System.out.println("Что то пошло не так");
                break;

        }
    }

    public void run() {
        isGameOver = false;
        int round = 1;
        while (!isGameOver) {
            FantasyGame.printLog("------Раунд: " + round++ + "------");
            //FantasyGame.printLog("Ход привилегированных персоонажей");
            //stepPrem(com1,com2);
            //stepPrem(com2,com1);
            FantasyGame.printLog("------Битва------");
            step(com1, com2);
            if (com1.avalibleCreatureCount() == 0) {
                isGameOver = true;
                FantasyGame.printLog("Победа команды - " + com2.getgName());
            } else if (com2.avalibleCreatureCount() == 0) {
                isGameOver = true;
                FantasyGame.printLog("Победа команды - " + com1.getgName());
            }
            renewDoStep(com1);
            renewDoStep(com2);

        }
        FantasyGame.printLog("------Game Over------");

    }
    public void stepPrem(Generation c1, Generation c2) { //batle com1 vs com2


            for (Creature c : c1.unit) {
                if (c.isPremium() && c.isAvalible()) {
                    if (c instanceof Elf.MagElf || c instanceof People.MagHuman) {
                        //Randomize of Mags(People and Elfs) spel and atack
                        if (random.nextInt(2) == 0) {
                            c.turn(randomCreature(c1));
                        } else {
                            c.turn(randomCreature(c2));
                        }
                    } else if (c instanceof Orks.Shaman) {
                        //Randomize of OrckShaman spel
                        if (random.nextInt(2) == 0) {
                            c.turn(randomCreature(c1));
                        } else {
                            c.turn(randomCreature(c2));
                        }
                    } else if (c instanceof Undead.Necromant) {
                        if (random.nextInt(2) == 0) {
                            c.turn(randomCreature(c2));
                        } else {
                            c.turn(randomCreature(c2));
                        }
                    }
                    c.setPremium(false); //Premium go out after action))

                }
            }

        //isGameOver=true;
    }

    public void step(Generation c1, Generation c2) {
        ArrayDeque<Creature> batleStek = new ArrayDeque<>();
        int counterC1 = 0;
        int counterC2 = 0;
        while (counterC1 < c1.avalibleCreatureCount() && counterC2 < c2.avalibleCreatureCount()) {
            //random add members from both team
                boolean isAdd = false;
                while (!isAdd) {
                    Creature tmpC1 = randomCreature(c1);
                    if (!batleStek.contains(tmpC1) && !tmpC1.isDoStep()) {
                         isAdd = batleStek.offerLast(tmpC1);
                         counterC1++;
                    }
                }
                isAdd = false;
                while (!isAdd) {
                    Creature tmpC2 = randomCreature(c2);
                    if (!batleStek.contains(tmpC2) && !tmpC2.isDoStep()) {
                        isAdd = batleStek.offerLast(tmpC2);
                        counterC2++;
                    }
                }
        }
        //FantasyGame.printLog("------Боевой стек сформирован------"); //temporary
        while (batleStek.peek() != null) {
            Creature doStepCreature = batleStek.poll();
            if (c1.unit.contains(doStepCreature) && doStepCreature.isAvalible()) { //creature from com1 and creature availible
                if (doStepCreature.race.equals(Creature.Race.ELF) || doStepCreature.race.equals(Creature.Race.PEOPLE)) {
                    if (random.nextInt(2) == 0) {
                        doStepCreature.turn(randomCreature(c1)); //do spel on friendly creature
                    } else {
                        doStepCreature.turn(randomCreature(c2));
                    } // atack enemy
                } else if (doStepCreature.race.equals(Creature.Race.ORKS)) {
                    if (random.nextInt(2) == 0) {
                        doStepCreature.turn(randomCreature(c1)); //do spel on friendly creature
                    } else {
                        doStepCreature.turn(randomCreature(c2));
                    } // atack enemy
                } else {
                    doStepCreature.turn(randomCreature(c2));
                } // atack enemy
            } else if (c2.unit.contains(doStepCreature) && doStepCreature.isAvalible()) { //creature from com2 and creature availible
                if (doStepCreature.race.equals(Creature.Race.ELF) || doStepCreature.race.equals(Creature.Race.PEOPLE)) {
                    if (random.nextInt(2) == 0) {
                        doStepCreature.turn(randomCreature(c2)); //do spel on friendly creature
                    } else {
                        doStepCreature.turn(randomCreature(c1)); // atack enemy
                    }
                } else if (doStepCreature.race.equals(Creature.Race.ORKS)) {
                    if (random.nextInt(2) == 0) {
                        doStepCreature.turn(randomCreature(c2)); //do spel on friendly creature
                    } else { // atack enemy
                        doStepCreature.turn(randomCreature(c1));
                    }
                } else {
                    doStepCreature.turn(randomCreature(c1));
                } // atack enemy
            }
        }
    }

    public Creature randomCreature(Generation com) {
        Generation avalible = new Generation();
        for (Creature g: com.unit) {
            if (g.isAvalible()) {
                avalible.unit.add(g);
            }
        }
        int rIndx = avalible.unit.size() == 0 ? 0 : random.nextInt(avalible.unit.size());

        return avalible.unit.get(rIndx);
    }

    public boolean renewDoStep(Generation g) { //Renew marker doStep for availible creature
        for (Creature c: g.unit) {
            if (c.isAvalible()) {
                c.setDoStep(false);
            }
        }
        return true;
    }

    public static boolean printLog(String s) {
        stringBuilder.append(s + "\n");
        System.out.println(s);

        return true;
    }


    public static void main(String[] args) {

        File file = new File("log.txt");

        game = new FantasyGame();
        game.run();
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(stringBuilder.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
