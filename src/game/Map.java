package game;

final class Map {
    char[][] map;

    Map(int length) {
        this.map = new char[length][length];
    }

    void fillMap() {
        for (int row = 0; row < this.map.length; row++) {
            for (int col = 0; col < this.map.length; col++) {
                this.map[row][col] = Items.empty;
            }
        }
    }

    void spawnBushes() {
        int count = (this.map.length * this.map.length) / Items.difficultPercent;
        while (count > 0) {
            int bushRow = Helper.giveMeRandomNum(this.map.length - 1);
            int bushCol = Helper.giveMeRandomNum(this.map.length - 1);
            if (Bush.canSpawnBush(this.map, bushRow, bushCol)) {
                this.map[bushRow][bushCol] = Items.bush;
                count--;
                //set bushCol position if bush spawn near down border
                if (bushRow == this.map.length - 1 && bushCol > Items.downBorderBushPosition) {
                    Items.downBorderBushPosition = bushCol;
                }
                //set bushRow position if bush spawn near right border
                if (bushCol == this.map.length - 1 && bushRow > Items.rightBorderBushPosition) {
                    Items.rightBorderBushPosition = bushRow;
                }
            }
        }
    }

    void spawnRock(Map map) {
        //add the rock
        while (true) {
            int row = Helper.giveMeRandomNum(Items.mapLength / 2);
            Rock.setRow(row, map);
            int col = Helper.giveMeRandomNum(Items.mapLength / 2);
            Rock.setCol(col, map);


            if (canSpawnRock(this.map, Rock.getRow(), Rock.getCol()) &&
                    Rock.isRockStuck(this.map, Rock.getRow(), Rock.getCol(), Items.rightBorderBushPosition, Items.downBorderBushPosition)) {
                this.map[Rock.getRow()][Rock.getCol()] = Items.rock;
                break;
            }
        }
    }

    void spawnPlayer(Map map) {
        int playerRow = Helper.giveMeRandomNum(this.map.length - 1);
        Player.setRow(playerRow, map);
        int playerCol = Helper.giveMeRandomNum(this.map.length - 1);
        Player.setCol(playerCol, map);
        this.map[Player.getRow()][Player.getCol()] = Items.people;
    }

    void setExit() {
        this.map[this.map.length - 1][this.map.length - 1] = Items.exit;
    }


    boolean canSpawnRock(char[][] matrix, int row, int col) {
        return (matrix[row][col] != Items.people && matrix[row][col] != Items.exit && matrix[row][col] != Items.bush);
    }

    void printMap() {
        for (int row = 0; row < this.map.length; row++) {

            if (row == 0) {
                for (int i = 0; i < this.map.length + 2; i++) {
                    System.out.print(Items.rowBorder);
                    if (i == this.map.length + 1) {
                        System.out.println();
                    }
                }

            }
            System.out.print(Items.colBorder);
            for (int col = 0; col < this.map[row].length; col++) {
                System.out.print(this.map[row][col]);
            }

            System.out.print(Items.colBorder);

            System.out.println();
            if (row == this.map.length - 1) {
                for (int i = 0; i < this.map.length + 2; i++) {
                    System.out.print(Items.rowBorder);
                }
            }

        }
    }
}
