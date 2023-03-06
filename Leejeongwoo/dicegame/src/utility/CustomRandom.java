package utility;

import java.util.Random;

public class CustomRandom {

    public static int min;
    public static int max;

    private static Random random = new Random();

    public static int createCustomRandom (int min, int max) {
        // Dice 클래스에서 이미 max : 6, min : 1로 지정했기 때문에
        // 1 ~ 6까지의 랜덤값 생성하려고
        return random.nextInt(max - min + 1) + min;
    }
}