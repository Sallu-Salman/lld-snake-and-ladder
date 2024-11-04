package model;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class Dice {
    @NonNull
    private int count;
    private final int MAX = 6;
    private final int MIN = 1;

    public int rollDice() {
        Random random = new Random();
        int value = 0;
        for(int i = 0; i < count; i++) {
            value += (random.nextInt(MAX) + MIN);
        }
        return value;
    }
}
