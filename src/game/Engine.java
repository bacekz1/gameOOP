package game;

import static game.Items.*;
import static game.Helper.*;

final class Engine {

    private static final int DOWN = -1;
    private static final int UP = 1;
    private static final int LEFT = 1;
    private static final int RIGHT = -1;

    static Map selectMap() {
        char difficult;
        do {
            System.out.println("Please select difficult from 1 to 3");
            String input = Helper.sc.nextLine();
            if (input.equals("dev")) {
                Map map = new Map(10);
                Player.setRow(5, map);
                Player.setCol(5, map);
                devMode(map);
                map.printMap();
                System.out.println("Exit from dev mode");
                beatTheMaze(map);
                success = false;
                gameOver = false;
                System.out.println("Please select difficult from 1 to 3");
                sc.nextLine();
                input = sc.nextLine();
            }
            difficult = input.charAt(0);
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

    static void devMode(Map map) {
        map.fillMap();
        while (true) {
            char curElem = map.map[Player.getRow()][Player.getCol()];
            int row = Player.getRow();
            int col = Player.getCol();
            map.map[Player.getRow()][Player.getCol()] = people;
            map.printMap();

            String input = sc.nextLine();
            if (input.isEmpty()) {
                continue;
            }
            if (input.equals("start")) {
                break;
            }
            char directions = input.charAt(0);

            switch (directions) {
                case 'w' -> devRowMove(map, UP);
                case 'a' -> devColMove(map, LEFT);
                case 's' -> devRowMove(map, DOWN);
                case 'd' -> devColMove(map, RIGHT);
                case 'b' -> devSetElement(map, bush);
                case 'r' -> devSetElement(map, rock);
                case 'e' -> devSetElement(map, exit);
                case 'z' -> devSetElement(map, empty);
            }
            if (map.map[row][col] == people) {
                map.map[row][col] = curElem;
            }
        }
    }

    static void collectCoins(Map map){

    }

    static void beatTheMaze(Map map) {

        while (!gameOver) {

            char directions = Helper.sc.next().charAt(0);

            switch (directions) {
                case 'w' -> beatMazeRowMove(map, UP);
                case 'a' -> beatMazeColMove(map, LEFT);
                case 's' -> beatMazeRowMove(map, DOWN);
                case 'd' -> beatMazeColMove(map, RIGHT);
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
        else {
            map.map[Player.getRow()][Player.getCol()] = deadPeople;
            map.printMap();
            System.out.println();
            System.out.println("Game over :(");
        }
    }

    private static void devSetElement(Map map, char element) {
        if (element == rock) {
            Rock.setRow(Player.getRow(), map);
            Rock.setCol(Player.getCol(), map);
        }
        map.map[Player.getRow()][Player.getCol()] = element;
    }

    private static void devColMove(Map map, int direction) {
        //game over
        if (isItOutOfBounds(map, Player.getRow() - direction)) {
            return;
        }
        Player.setCol(Player.getCol() - direction, map);
    }

    private static void devRowMove(Map map, int direction) {
        //game over
        if (isItOutOfBounds(map, Player.getRow() - direction)) {
            return;
        }
        Player.setRow(Player.getRow() - direction, map);
    }

    private static void beatMazeRowMove(Map map, int direction) {
        //game over
        if (isItOutOfBounds(map, Player.getRow() - direction)) {
            gameOver = true;
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
                gameOver = true;
                return;
            }
            if (map.map[Rock.getRow() - direction][Rock.getCol()] == exit) {
                success = true;
                gameOver = true;
                return;
            }
            //bush logic
            if (map.map[Rock.getRow() - direction][Rock.getCol()] == bush) {
                return;
            }
            map.map[Player.getRow()][Player.getCol()] = empty;
            Player.setRow(Player.getRow() - direction, map);
            map.map[Player.getRow()][Player.getCol()] = people;
            Rock.setRow(Rock.getRow() - direction, map);
            map.map[Rock.getRow()][Rock.getCol()] = rock;
            return;
        }
        map.map[Player.getRow()][Player.getCol()] = empty;
        Player.setRow(Player.getRow() - direction, map);
        map.map[Player.getRow()][Player.getCol()] = people;
    }

    private static void beatMazeColMove(Map map, int direction) {
        //game over
        if (isItOutOfBounds(map, Player.getCol() - direction)) {
            gameOver = true;
            return;
        }
        //bush logic
        if (map.map[Player.getRow()][Player.getCol() - direction] == bush) {
            return;
        }
        //rock logic
        if (map.map[Player.getRow()][Player.getCol() - direction] == rock) {
            //game over
            if (isItOutOfBounds(map, Rock.getCol() - direction)) {
                gameOver = true;
                return;
            }
            if (map.map[Rock.getRow()][Rock.getCol() - direction] == exit) {
                success = true;
                gameOver = true;
                return;
            }
            //bush logic
            if (map.map[Rock.getRow()][Rock.getCol() - direction] == bush) {
                return;
            }
            map.map[Player.getRow()][Player.getCol()] = empty;
            Player.setCol(Player.getCol() - direction, map);
            map.map[Player.getRow()][Player.getCol()] = people;
            Rock.setCol(Rock.getCol() - direction, map);
            map.map[Rock.getRow()][Rock.getCol()] = rock;
            return;
        }
        map.map[Player.getRow()][Player.getCol()] = empty;
        Player.setCol(Player.getCol() - direction, map);
        map.map[Player.getRow()][Player.getCol()] = people;
    }

    private static void collectCoinsRowMove(Map map, int direction) {
        //game over
        if (isItOutOfBounds(map, Player.getRow() - direction)) {
            gameOver = true;
            return;
        }


        map.map[Player.getRow()][Player.getCol()] = empty;
        Player.setRow(Player.getRow() - direction, map);
        map.map[Player.getRow()][Player.getCol()] = people;
    }

    private static boolean isItOutOfBounds(Map map, int position) {
        return (position < 0 || position > map.map.length - 1);
    }

    private static boolean isRockStuck(char[][] map) {
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