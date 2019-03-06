package com.example.nalcorn.basementsandbasilisks;

import java.util.Random;

/**
 * Created by nicho_000 on 11/21/2015.
 */
public class Creature {
    /*Variables
    *******************************************************************/
    private enum creature {
        FEMALE_ARCHER, FEMALE_FIGHTER, FEMALE_MAGE,
        MALE_ARCHER, MALE_FIGHTER, MALE_MAGE,
        CYCLOPS, DRAGON, GORGON,
        SPIDER, WOLF, ZOMBIE
    }
    private creature myCreature;
    private int HP = 0,
                attackValue = 0,
                goldValue = 0;
    private String name;

    /*Constructors
    *******************************************************************/
    public Creature(){

    }
    public Creature(String choice){
        switch (choice.toLowerCase()) {
            case "female_archer":
                myCreature = creature.FEMALE_ARCHER;
                break;
            case "female_fighter":
                myCreature = creature.FEMALE_FIGHTER;
                break;
            case "female_mage":
                myCreature = creature.FEMALE_MAGE;
                break;
            case "male_archer":
                myCreature = creature.MALE_ARCHER;
                break;
            case "male_fighter":
                myCreature = creature.MALE_FIGHTER;
                break;
            case "male_mage":
                myCreature = creature.MALE_MAGE;
                break;
            case "cyclops":
                myCreature = creature.CYCLOPS;
                break;
            case "dragon":
                myCreature = creature.DRAGON;
                break;
            case "gorgon":
                myCreature = creature.GORGON;
                break;
            case "spider":
                myCreature = creature.SPIDER;
                break;
            case "wolf":
                myCreature = creature.WOLF;
                break;
            case "zombie":
                myCreature = creature.ZOMBIE;
                break;
        }
        setCreature(myCreature);
    }

    /*Getters
    *******************************************************************/
    public int getHP(){
        return HP;
    }
    public int getGoldValue(){
        return goldValue;
    }
    public String getName(){
        return name;
    }


    /*Setters
    *******************************************************************/
    public void setCreature(String choice){
        switch (choice.toLowerCase()) {
            case "female_archer":
                myCreature = creature.FEMALE_ARCHER;
                break;
            case "female_fighter":
                myCreature = creature.FEMALE_FIGHTER;
                break;
            case "female_mage":
                myCreature = creature.FEMALE_MAGE;
                break;
            case "male_archer":
                myCreature = creature.MALE_ARCHER;
                break;
            case "male_fighter":
                myCreature = creature.MALE_FIGHTER;
                break;
            case "male_mage":
                myCreature = creature.MALE_MAGE;
                break;
            case "cyclops":
                myCreature = creature.CYCLOPS;
                break;
            case "dragon":
                myCreature = creature.DRAGON;
                break;
            case "gorgon":
                myCreature = creature.GORGON;
                break;
            case "spider":
                myCreature = creature.SPIDER;
                break;
            case "wolf":
                myCreature = creature.WOLF;
                break;
            case "zombie":
                myCreature = creature.ZOMBIE;
                break;
        }
        setCreature(myCreature);
    }
    public void setCreature(creature choice){

        switch (myCreature) {
            case CYCLOPS:
                setHP(20);
                setAttackValue(6);
                setGoldValue(attackValue*1000);

                break;
            case DRAGON:
                setHP(30);
                setAttackValue(10);
                setGoldValue(attackValue*1000);

                break;
            case GORGON:
                setHP(15);
                setAttackValue(5);
                setGoldValue(attackValue*1000);

                break;
            case SPIDER:
                setHP(15);
                setAttackValue(5);
                setGoldValue(attackValue*1000);

                break;
            case WOLF:
                setHP(10);
                setAttackValue(4);
                setGoldValue(attackValue*1000);

                break;
            case ZOMBIE:
                setHP(10);
                setAttackValue(3);
                setGoldValue(attackValue*1000);
                break;
            default:
                setHP(15);
                setAttackValue(5);
                setName(myCreature);
                break;
        }
    }


    public void setHP(int HP) {
        this.HP = HP;
    }
    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }
    public void setGoldValue(int goldValue) {
        this.goldValue = goldValue;
    }
    public void setName(creature myCreature){
        switch (myCreature) {
            case FEMALE_ARCHER:
                name = "Female Archer";
                break;
            case FEMALE_FIGHTER:
                name = "Female Fighter";
                break;
            case FEMALE_MAGE:
                name = "Female Mage";
                break;
            case MALE_ARCHER:
                name = "Male Archer";
                break;
            case MALE_FIGHTER:
                name = "Male Fighter";
                break;
            case MALE_MAGE:
                name = "Male Mage";
                break;
            default:
                name = "";
                break;
        }
    }

    /*Methods
    *******************************************************************/
    public int attack(){
        Random r = new Random();
        int Low = 0;
        int High = attackValue+1;
        int damage = r.nextInt(High-Low) + Low;
        return damage;
    }
    public int monsterAttack(){
        int damage = 0;
        Random i = new Random();
        int Low = 1;
        int High = 4;
        int result = i.nextInt(High-Low) + Low;
        if(result == 1) {
            Random r = new Random();
            int min = 1;
            int max = attackValue+1;
            damage = r.nextInt(max - min) + min;
        }
        return damage;
    }
    public void takeDamage(int damage){
        setHP(getHP()-damage);
        if(getHP()<0){
            setHP(0);
        }
    }
    public void heal(int heal){
        setHP(getHP()+heal);
        if(getHP()>15){
            setHP(15);
        }
    }

}
