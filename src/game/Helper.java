package game;

import java.util.Random;
import java.util.Scanner;

final class Helper {

    static Scanner sc = new Scanner(System.in);

    static int giveMeRandomNum(int to) {
        Random random = new Random();
        return random.nextInt(to);
    }
}
