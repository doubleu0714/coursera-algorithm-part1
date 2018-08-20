import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private boolean[] grid;
	private final int n;
	private final int virtualTopIndex;
	private final int virtualBottomIndex;
	private int numberOfOpenSites;
	private final WeightedQuickUnionUF wqu;
	
	public Percolation(int n) {
		if(n <= 0) throw new IllegalArgumentException();
		this.n = n;
		this.virtualTopIndex = n * n;
		this.virtualBottomIndex = n * n + 1;
		this.numberOfOpenSites = 0;
		this.grid = new boolean[n * n];
		for(int i = 0 ; i < grid.length ; i++) {
			grid[i] = false;
		}
		wqu = new WeightedQuickUnionUF(n * n + 2);
		
		StringBuffer sb = new StringBuffer();
		sb.append("n = ");
		sb.append(n);
		sb.append("\n");
		sb.append("virtualTop = ");
		sb.append(virtualTopIndex);
		sb.append("\n");
		sb.append("virtualBottom = ");
		sb.append(virtualBottomIndex);
		sb.append("\n");
		sb.append("grid.length = ");
		sb.append(grid.length);
		sb.append("\n");
	}
	
	private int getIndex(int row, int col) {
		if(row > n || row <= 0 || col > n || col <= 0) throw new IllegalArgumentException();
		return (row - 1) * n + col - 1;
	}
	
	public void open(int row, int col) {
		if(row > n || row <= 0 || col > n || col <= 0) throw new IllegalArgumentException();
		
		if(!grid[getIndex(row, col)]) {
			grid[getIndex(row, col)] = true;
			numberOfOpenSites++;
		}
		
		//up site union
		if(row - 1 > 0 && isOpen(row - 1, col)) {
			wqu.union(getIndex(row, col), getIndex(row - 1, col));
		}
		
		//down site union
		if(row + 1 <= n && isOpen(row + 1, col)) {
			wqu.union(getIndex(row, col), getIndex(row + 1, col));
		}
		
		//right site union
		if(col + 1 <= n && isOpen(row, col + 1)) {
			wqu.union(getIndex(row, col), getIndex(row, col + 1));
		}
		
		//left site union
		if(col - 1 > 0 && isOpen(row, col - 1)) {
			wqu.union(getIndex(row, col), getIndex(row, col - 1));
		}
		
		if(row == 1) {
			wqu.union(getIndex(row, col), virtualTopIndex);
		}
		
		if(row == n) {
			wqu.union(getIndex(row, col), virtualBottomIndex);
		}
	}
	
	public boolean isOpen(int row, int col) {
		if(row > n || row <= 0 || col > n || col <= 0) throw new IllegalArgumentException();
		
		return grid[getIndex(row, col)];
	}
	
	public boolean isFull(int row, int col) {
		if(row > n || row <= 0 || col > n || col <= 0) throw new IllegalArgumentException();
		if(!grid[getIndex(row, col)]) {
			return false;
		}
		return wqu.connected(getIndex(row, col), virtualTopIndex);
	}
	
	public int numberOfOpenSites() {
		return numberOfOpenSites;
	}
	
	public boolean percolates() {
		return wqu.connected(virtualTopIndex, virtualBottomIndex);
	}
	/*
	public static void main(String... args) {
		try(BufferedReader br = new BufferedReader(new FileReader(new File(args[0])))) {
			String strN = br.readLine();
			Percolation percolation = new Percolation(Integer.parseInt(strN));
			
			String line = "";
			while((line = br.readLine()) != null) {
				String[] values = line.trim().split(" ");
                int i = Integer.parseInt(values[0].trim());
                int j = Integer.parseInt(values[1].trim());
                percolation.open(i, j);
			}
			System.out.println(percolation.percolates());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
}
