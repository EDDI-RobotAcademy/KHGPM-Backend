package utility;

import java.util.Random;

public class CustomRandom {
    private static int max=6;
    private static int min=1;

    private static Random random = new Random();

    public static int customRandom(int max, int min){
        return random.nextInt(max-min+1) + min;
    }



}
