package game;

class Items {
    static final char empty = ' ';
    static final char rowBorder = '-';
    static final char colBorder = '|';
    static final char exit = 'E';
    static final char people = 'P';
    static final char deadPeople = 'D';
    static final char rock = 'R';
    static final char bush = '#';
    static final int zero = 0;

    static final char difficult = 0;
    static  int mapLength = 0;
    static int difficultPercent = 0;
    static boolean gameOver = false;
    static boolean success = false;
    static int rightBorderBushPosition = -1;
    static int downBorderBushPosition = -1;
}
