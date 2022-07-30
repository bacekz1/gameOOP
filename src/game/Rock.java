package game;

final class Rock {
    private static int row;
    private static int col;

    static public int getRow() {
        return row;
    }

    static void setRow(int row, Map map) {
        if (row < game.Items.zero || row >= map.map[0].length) {
            System.out.printf("Rock col length should be between %d and %d", game.Items.zero, map.map[0].length);
        } else {
            Rock.row = row;
        }
    }

    static public int getCol() {
        return col;
    }

    static void setCol(int col, Map map) {
        if (col < game.Items.zero || col >= map.map[0].length) {
            System.out.printf("Rock col length should be between %d and %d", game.Items.zero, map.map[0].length);
        } else {
            Rock.col = col;
        }
    }

    static boolean isRockStuck(char[][] matrix, int rockRow, int rockCol,
                               int rightBorderBushPosition, int downBorderBushPosition) {
        boolean upBorder = rockRow < 1;
        boolean leftBorder = rockCol < 1;
        boolean downBorder = rockCol <= matrix.length - 1 && rightBorderBushPosition > rockRow;
        boolean rightBorder = rockRow <= matrix.length - 1 && downBorderBushPosition > rockCol;
        boolean leftBush = rockCol > 0 && matrix[rockRow][rockCol - 1] == Items.bush;
        boolean rightBush = rockCol < matrix.length - 1 && matrix[rockRow][rockCol + 1] == Items.bush;
        boolean upBush = rockRow > 0 && matrix[rockRow - 1][rockCol] == Items.bush;
        boolean downBush = rockRow < matrix.length - 1 && matrix[rockRow + 1][rockCol] == Items.bush;


        //if the rock is stuck return false
        return !(upBorder || leftBorder || downBorder || rightBorder || (leftBush && upBush) || (leftBush && downBush) ||
                (rightBush && upBush) || (rightBush && downBush));
    }
}
