package game;

import static game.Items.*;

final class Engine {

    private static final int DOWN = -1;
    private static final int UP = 1;
    private static final int LEFT = 1;
    private static final int RIGHT = -1;

    static Map selectMap() {

        int difficult;

        do {
            System.out.println("Please select difficult from 1 to 3");
            difficult = Helper.sc.nextLine().charAt(0);
            if (difficult > '3' || difficult < '1') {
                System.out.println("Invalid input :-/");
            }

            switch (difficult) {
                case '1' -> {
                    Items.mapLength = 12;
                    //12.5 percents bushes
                    Items.difficultPercent = 8;
                }
                case '2' -> {
                    Items.mapLength = 17;
                    //20 percents bushes
                    Items.difficultPercent = 5;
                }
                case '3' -> {
                    Items.mapLength = 27;
                    //33.33 percents bushes
                    Items.difficultPercent = 3;
                }
            }
        }
        while (difficult < '1' || difficult > '3');
        return new Map(Items.mapLength);

    }

    static void starGame(Map map) {

        while (!Items.gameOver) {

            char directions = Helper.sc.next().charAt(0);

            switch (directions) {
                //up
                case 'w' -> rowMove(map, UP);
                case 'a' -> colMove(map, LEFT);
                case 's' -> rowMove(map, DOWN);
                case 'd' -> colMove(map, RIGHT);
            }
            //success condition
            if (Rock.getRow() == Items.mapLength - 1 && Rock.getCol() == Items.mapLength - 1) {
                Items.success = true;
                break;
            }

            //game.Rock game over logic
            if (!isRockStuck(map.map)) {
                System.out.println("The rock can`t move :=/");
                gameOver = true;
                break;
            }
            map.printMap();
        }
        if (success) {
            map.printMap();
            System.out.println();
            System.out.println("Congrats you win the game");

        }
        if (gameOver) {
            map.map[Player.getRow()][Player.getCol()] = deadPeople;
            map.printMap();
            System.out.println();
            System.out.println("Game over :(");
        }

    }

    private static void rowMove(Map map, int direction) {
        //game over
        if (isItOutOfBounds(map, Player.getRow() - direction)) {
            Items.gameOver = true;
            return;
        }
        //bush logic
        if (map.map[Player.getRow() - direction][Player.getCol()] == bush) {
            return;
        }
        //rock logic
        if (map.map[Player.getRow() - direction][Player.getCol()] == Items.rock) {
            //game over
            if (isItOutOfBounds(map, Rock.getRow() - direction)) {
                Items.gameOver = true;
                return;
            }
            //bush logic
            if (map.map[Rock.getRow() - direction][Rock.getCol()] == bush) {
                return;
            }
            map.map[Player.getRow()][Player.getCol()] = Items.empty;
            Player.setRow(Player.getRow()-direction,map);
            map.map[Player.getRow()][Player.getCol()] = Items.people;
            Rock.setRow(Rock.getRow() -direction,map);
            map.map[Rock.getRow()][Rock.getCol()] = Items.rock;
            return;
        }
        map.map[Player.getRow()][Player.getCol()] = Items.empty;
        Player.setRow(Player.getRow()-direction,map);
        map.map[Player.getRow()][Player.getCol()] = Items.people;
    }

    private static void colMove(Map map, int direction) {
        //game over
        if (isItOutOfBounds(map, Player.getCol() - direction)) {
            Items.gameOver = true;
            return;
        }
        //bush logic
        if (map.map[Player.getRow()][Player.getCol() - direction] == bush) {
            return;
        }
        //rock logic
        if (map.map[Player.getRow()][Player.getCol() - direction] == Items.rock) {
            //game over
            if (isItOutOfBounds(map, Rock.getCol() - direction)) {
                Items.gameOver = true;
                return;
            }
            //bush logic
            if (map.map[Rock.getRow()][Rock.getCol() - direction] == bush) {
                return;
            }
            map.map[Player.getRow()][Player.getCol()] = Items.empty;
            Player.setCol(Player.getCol() -direction,map);
            map.map[Player.getRow()][Player.getCol()] = Items.people;
            Rock.setCol(Rock.getCol() - direction,map);
            map.map[Rock.getRow()][Rock.getCol()] = Items.rock;
            return;
        }
        map.map[Player.getRow()][Player.getCol()] = Items.empty;
        Player.setCol(Player.getCol() -direction,map);
        map.map[Player.getRow()][Player.getCol()] = Items.people;
    }

   private static boolean isItOutOfBounds(Map map, int position) {
        return (position < 0 || position > map.map.length - 1);
    }

   private static boolean isRockStuck(char [][] map) {
        boolean upBorder = Rock.getRow() < 1;
        boolean leftBorder = Rock.getCol() < 1;
        boolean downBorder = Rock.getCol() <= map.length - 1 && rightBorderBushPosition > Rock.getRow();
        boolean rightBorder = Rock.getRow() <= map.length - 1 && downBorderBushPosition > Rock.getCol();
        boolean leftBush = Rock.getCol() > 0 && map[Rock.getRow()][Rock.getCol() - 1] == bush;
        boolean rightBush = Rock.getCol() < map.length - 1 && map[Rock.getRow()][Rock.getCol() + 1] == bush;
        boolean upBush = Rock.getRow() > 0 && map[Rock.getRow() - 1][Rock.getCol()] == bush;
        boolean downBush = Rock.getRow() < map.length - 1 && map[Rock.getRow() + 1][Rock.getCol()] == bush;

        //if the rock is stuck return false
        return !(upBorder || leftBorder || downBorder || rightBorder || (leftBush && upBush) || (leftBush && downBush) ||
                (rightBush && upBush) || (rightBush && downBush));
    }
}