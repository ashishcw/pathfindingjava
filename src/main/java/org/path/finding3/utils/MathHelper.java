package org.path.finding3.utils;

import java.util.Random;

public class MathHelper {
    public static int GetRandomNumber(int min, int max){
        Random rnd = new Random();
        return rnd.nextInt(min, max);
    }
}
