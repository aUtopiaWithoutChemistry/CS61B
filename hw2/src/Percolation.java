import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private int size;
    private WeightedQuickUnionUF uf;
    private int openedSites = 0;
    private int[][] status; // -1 is blocked, 0 is open
    private int lastFullElement = -1;

    public Percolation(int N) {

        if (N > 0) {
            // O(N^2)
            uf = new WeightedQuickUnionUF(N * N);
            size = N;

            // use a 2D array to track the status, initialized as blocked
            status = new int[N][N];
            // O(N^2)
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    status[i][j] = -1;
                }
            }

            // connect all the bottom brick together in the djs
            for (int i = 1; i < size; i++) {
                uf.union((size * (size - 1)), (i + size * (size - 1)));
            }

            //System.out.println(status);
        }
        else{
            throw new IllegalArgumentException("N should be an integer greater than 0");
        }
    }

    public void open(int row, int col) {

        // O(1)
        if (row < size && col < size) {
            status[row][col] = 0;
            openedSites += 1;

            // check four neighbor of this is opened, once opened then union
            if (row - 1 >= 0 && status[row - 1][col] == 0) {
                uf.union(row * size + col, uf.find((row - 1) * size + col));
            }
            if (row + 1 < size && status[row + 1][col] == 0) {
                uf.union(row * size + col, uf.find((row + 1) * size + col));
            }
            if(col - 1 >= 0 && status[row][col - 1] == 0) {
                uf.union(row * size + col, uf.find(row * size + col - 1));
            }
            if (col + 1 < size && status[row][col + 1] == 0) {
                uf.union(row * size + col, uf.find(row * size + col + 1));
            }

            // every first row once opened should be full
            if (row == 0) {
                if (lastFullElement != -1) {
                    uf.union(lastFullElement, col);
                }
                lastFullElement = col;
            }
        }
        else {
            throw new IndexOutOfBoundsException("row and col should be lower or equals to " + size);
        }
    }

    public boolean isOpen(int row, int col) {

        // O(1)
        boolean isOpen = false;
        if (row < size && col < size) {
            if (status[row][col] == 0 || status[row][col] == 1) {
                isOpen = true;
            }
        } else {
            new IndexOutOfBoundsException("row and col should be lower or equals to " + size);
        }
        return isOpen;
    }

    public boolean isFull(int row, int col) {

        // O(1)
        boolean isFull = false;
        if (row < size && col < size && row >= 0 && col >= 0) {
            if (lastFullElement != -1) {
                if (uf.connected(row * size + col, uf.find(lastFullElement))) {
                    isFull = true;
                }
            }
            else {
                isFull = false;
            }
        }
        else {
            throw new IndexOutOfBoundsException("row and col should be lower or equals to " + size);
        }
        return isFull;
    }

    public int numberOfOpenSites() {

        // O(1)
        return openedSites;
    }

    public boolean percolates() {

        // check if the first node of the bottom connected to the full set
        // because all the bottom are connected when initialization
        // O(1)
        if (lastFullElement != -1 || size < 0){
            if (uf.connected(size * (size - 1) + 1, lastFullElement)) {
                return true;
            }
        }
        return false;
    }

}
