package game;

class Player {
    private static int row;
    private static int col;

    private static int coins = 0;

    static int getCoins() {
        return coins;
    }

    static void collectCoins() {
        Player.coins++;
    }
    static void exchangeCoins(int coins) {
        if (Player.coins>= coins) {
            Player.coins -= coins;
        }
        else {
            System.out.println("You haven`t enough coins to buy this item :-/");
        }
    }

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
