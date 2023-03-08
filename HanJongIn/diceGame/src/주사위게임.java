import java.util.Objects;
import java.util.Scanner;


public class 주사위게임 {


    public int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = (int) (Math.random() * 6) + 1;
        System.out.println(a);

    }
}
