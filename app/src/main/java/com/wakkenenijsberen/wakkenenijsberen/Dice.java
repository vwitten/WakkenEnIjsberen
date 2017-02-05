package com.wakkenenijsberen.wakkenenijsberen;

import java.util.Random;

/**
 * Created by anferney on 7-12-2016.
 */
public class Dice {
    private int amount;

    public Dice(){

    }

    public int[] Throw(int _amountDices){
        Random rand = new Random();
        int[] array = new int[_amountDices];

        for(int i = 0; i < _amountDices; i++){
            array[i] = rand.nextInt(6) + 1;
        }

        return array;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
