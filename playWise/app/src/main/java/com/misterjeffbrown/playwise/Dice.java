package com.misterjeffbrown.playwise;

import java.util.Random;

/**
 * Created by kyle on 4/6/2015.
 * Features to Implement:
 *      Write test cases
 *      User range
 *      Save calculations
 *      Redo recursive probability call(crazy inefficient)
 *          -Not a priority if I save calculations
 */
public class Dice {
    private int numOfFaces;
    private int numOfDice;
    private int highestHit;
    private int exactUserNum;
    private int userRangeMin;
    private int userRangeMax;

    public Dice(int Faces, int DiceNum, int exactInput){
        this.numOfFaces = Faces;
        this.numOfDice = DiceNum;
        this.highestHit = Faces * DiceNum;
        this.exactUserNum = exactInput;
    }

    public Dice(int Faces, int DiceNum, int RangeStart, int RangeEnd){
        this.numOfFaces = Faces;
        this.numOfDice = DiceNum;
        this.userRangeMin = RangeStart;
        this.userRangeMax = RangeEnd;
    }

    public Dice(){
        this.numOfFaces = 6;
        this.numOfDice = 1;
        this.exactUserNum = 1;
    }

    public int getNumOfFaces(){
        return numOfFaces;
    }

    public int getNumOfDice(){
        return numOfDice;
    }

    public int getHighestHit() {
        return highestHit;
    }

    public int getExactUserNum() {
        return exactUserNum;
    }

    public int getUserRangeMin() {
        return userRangeMin;
    }

    public int getUserRangeMax() {
        return userRangeMax;
    }

    public void setExactUserNum(int exactUserNum) {
        this.exactUserNum = exactUserNum;
    }

    public void setNumOfDice(int numOfDice) {
        this.numOfDice = numOfDice;
    }

    public void setNumOfFaces(int numOfFaces) {
        this.numOfFaces = numOfFaces;
    }

    public void setUserRangeMin(int userRangeMin) {
        this.userRangeMin = userRangeMin;
    }

    public void setUserRangeMax(int userRangeMax) {
        this.userRangeMax = userRangeMax;
    }


    public double calcProbability(Dice d) {
        int faces = d.getNumOfFaces();
        int diceNum = d.getNumOfDice();
        int exactHit = d.getExactUserNum();
        if (diceNum == 0) {
            if (exactHit == 0) {
                return 1.0;
            } else {
                return 0.0;
            }
        } else {
            double sum = 0.0;
            for (int i = exactHit - faces; i < exactHit; i++) {
                d.setNumOfDice(diceNum - 1);
                d.setExactUserNum(i);
                sum += calcProbability(d) / faces;
            }
            return sum;
        }

    }

    /*
    public double calcProbability(int faces, int dice, int exactHit){
        int i;
        int temp = 0;
        //lowest possible roll is 1 * dice
        if(exactHit < dice){
            return 88;
        }
        //highest possible roll is faces * dice
        else if (exactHit > this.getHighestHit()){
            return 1221;
        }
        else{
            for(i = 1; i <= dice; i++){
                if (dice >= 1) temp += calcProbability(faces, dice - 1, exactHit);
            }
        }
        return (temp / this.getHighestHit());
    }
    */
}
