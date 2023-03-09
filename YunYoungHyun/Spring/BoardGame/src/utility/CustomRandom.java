package utility;

import java.util.Random;

/***
 * 주사위 굴리는 클래스
 */
public class CustomRandom {

    final private static int START = 0;
    public static int min;
    public static int max;

    private static Random random = new Random();

    public static int createCustomRandom(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static int createCustomRandom (int length) {
        return createCustomRandom(START, length - 1);
    }

}