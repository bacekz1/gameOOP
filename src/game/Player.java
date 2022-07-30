package game;

class Player {
    private static int row;
    private static int col;
    //todo

    static int getRow() {
        return row;
    }

    static void setRow(int row, Map map) {
        if (row < game.Items.zero || row >= map.map.length) {
            System.out.printf("Player row length should be between %d and %d", game.Items.zero, map.map.length);
        } else {
            Player.row = row;
        }
    }

    static int getCol() {
        return col;
    }

   static void setCol(int col, Map map) {
        if (col < game.Items.zero || col >= map.map[0].length) {
            System.out.printf("Player col length should be between %d and %d", game.Items.zero, map.map[0].length);
        } else {
            Player.col = col;
        }
    }
}
