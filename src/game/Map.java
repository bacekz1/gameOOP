package game;

import static game.Helper.*;
import static game.Items.*;

final class Map {
    char[][] map;

    Map(int length) {
        this.map = new char[length][length];
    }

    void spawnCoins(Map map) {
        int count = map.map.length*2;
        while (count > 0) {
            int coinRow = giveMeRandomNum(this.map.length-1);
            int coinCol = giveMeRandomNum(this.map.length-1);
            if (map.map[coinRow][coinCol] == people || map.map[coinRow][coinRow] == coin) {
                continue;
            }
            map.map[coinRow][coinCol] = coin;
            count--;
        }
    }

    void fillMap() {
        for (int row = 0; row < this.map.length; row++) {
            for (int col = 0; col < this.map.length; col++) {
                this.map[row][col] = empty;
            }
        }
    }

    void spawnBushes() {
        int count = (this.map.length * this.map.length) / Items.difficultPercent;
        while (count > 0) {
            int bushRow = giveMeRandomNum(this.map.length - 1);
            int bushCol = giveMeRandomNum(this.map.length - 1);
            if (Bush.canSpawnBush(this.map, bushRow, bushCol)) {
                this.map[bushRow][bushCol] = Items.bush;
                count--;
                //set bushCol position if bush spawn near down border
                if (bushRow == this.map.length - 1 && bushCol > downBorderBushPosition) {
                    Items.downBorderBushPosition = bushCol;
                }
                //set bushRow position if bush spawn near right border
                if (bushCol == this.map.length - 1 && bushRow > rightBorderBushPosition) {
                    rightBorderBushPosition = bushRow;
                }
            }
        }
    }

    void spawnRock(Map map) {
        //add the rock
        while (true) {
            int row = giveMeRandomNum(mapLength / 2);
            Rock.setRow(row, map);
            int col = giveMeRandomNum(mapLength / 2);
            Rock.setCol(col, map);


            if (canSpawnRock(this.map, Rock.getRow(), Rock.getCol()) &&
                    Rock.isRockStuck(this.map, Rock.getRow(), Rock.getCol(),
                            rightBorderBushPosition, downBorderBushPosition)) {
                this.map[Rock.getRow()][Rock.getCol()] = Items.rock;
                break;
            }
        }
    }

    void spawnPlayer(Map map) {
        int playerRow = giveMeRandomNum(this.map.length - 1);
        Player.setRow(playerRow, map);
        int playerCol = giveMeRandomNum(this.map.length - 1);
        Player.setCol(playerCol, map);
        this.map[Player.getRow()][Player.getCol()] = people;
    }

    void setExit() {
        this.map[this.map.length - 1][this.map.length - 1] = exit;
    }


    boolean canSpawnRock(char[][] matrix, int row, int col) {
        return (matrix[row][col] != people && matrix[row][col] != exit && matrix[row][col] != bush);
    }

    void printCoinMap() {
        System.out.printf("steps:%d coins:%d",Player.getSteps(),Player.getCoins());
        System.out.println();
        for (int row = 0; row < this.map.length; row++) {

            if (row == 0) {
                for (int i = 0; i < this.map.length + 2; i++) {
                    System.out.print(Items.rowBorder);
                    if (i == this.map.length + 1) {
                        System.out.println();
                    }
                }
            }
            System.out.print(colBorder);
            for (int col = 0; col < this.map[row].length; col++) {
                System.out.print(this.map[row][col]);
            }
            System.out.print(colBorder);
            System.out.println();
            if (row == this.map.length - 1) {
                for (int i = 0; i < this.map.length + 2; i++) {
                    System.out.print(rowBorder);
                }
            }

        }
    }

    void printMazeMap() {
        for (int row = 0; row < this.map.length; row++) {

            if (row == 0) {
                for (int i = 0; i < this.map.length + 2; i++) {
                    System.out.print(Items.rowBorder);
                    if (i == this.map.length + 1) {
                        System.out.println();
                    }
                }
            }
            System.out.print(colBorder);
            for (int col = 0; col < this.map[row].length; col++) {
                System.out.print(this.map[row][col]);
            }
            System.out.print(colBorder);
            System.out.println();
            if (row == this.map.length - 1) {
                for (int i = 0; i < this.map.length + 2; i++) {
                    System.out.print(rowBorder);
                }
            }

        }
    }
}
