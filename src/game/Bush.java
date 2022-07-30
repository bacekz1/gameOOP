package game;

class Bush {

    private int row;
    private int col;

     static boolean canSpawnBush(char[][] matrix, int row, int col) {
//         if (row == matrix.length - 1 && col < matrix.length - 1) {
//             col++;
//         }
//         if (col == matrix.length - 1 && row < matrix.length - 1) {
//             row++;
//         }
         return (matrix[row][col] != Items.people && matrix[row][col] != Items.rock && matrix[row][col] != Items.exit);
     }

}
