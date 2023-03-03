package utility;

import java.util.Random;

public class CustomRandom {

    public static int min;
    public static int max;

    private static Random random = new Random();

    /**
     * createCustomRandom 함수 선언
     * @param min 최솟값
     * @param max 최대값
     * @return
     */
    public static int createCustomRandom (int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
