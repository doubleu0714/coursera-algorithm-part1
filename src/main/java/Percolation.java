public class Percolation {
    private boolean[][] grid;
    private int countOfOpenSite;
    public Percolation(int n) {
       grid = new boolean[n][n];
       for(int i = 0 ; i < n ; i++) {
           for(int j = 0 ; j < n ; j++) {
               grid[i][j] = false;
           }
       } 
       this.countOfOpenSite = 0;
    }

    public void open(int row, int col) {
        if(!grid[row - 1][col - 1]) {
            countOfOpenSite++;
            grid[row - 1][col - 1] = true;
        }
    }

    public boolean isOpen(int row, int col) {
        return grid[row - 1][col - 1];
    }
}