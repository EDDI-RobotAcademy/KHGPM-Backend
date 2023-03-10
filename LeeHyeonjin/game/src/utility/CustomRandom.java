package utility;

import java.util.Random;

public class CustomRandom {
    private static int max;
    private static int min;

    private static Random random = new Random();

    public static int createCustomRandom(int max, int min){
        return random.nextInt(max-min+1) + min;
    }



}
