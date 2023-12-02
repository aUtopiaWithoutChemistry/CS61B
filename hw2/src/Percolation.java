import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private int size;
    private WeightedQuickUnionUF uf;
    private int openedSites = 0;
    private int[][] status; // -1 is blocked, 0 is open
    private int lastFullElement = -1;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        if (N > 0) {
            uf = new WeightedQuickUnionUF(N * N);
            size = N;

            // use a 2D array to track the status, initialized as blocked
            status = new int[N][N];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    status[i][j] = -1;
                }
            }

            //System.out.println(status);
        }
        else{
            throw new IllegalArgumentException("N should be an integer greater than 0");
        }
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
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

            //
            //if ()

//            // check four neighbor of this is full,
//            // if they are full, and this brick is open, then this is full
//            if (((row - 1 >= 0 && status[row - 1][col] == 1)
//                    || (row + 1 < size && status[row + 1][col] == 1)
//                    || (col - 1 >= 0 && status[row][col - 1] == 1)
//                    || (col + 1 < size && status[row][col + 1] == 1))
//                    && status[row][col] == 0) {
//                status[row][col] = 1;
//            }
//
//            // check four neighbor of this is full,
//            // if they are not full but open, and this brick is full, then they are full
//            if (status[row][col] == 1) {
//                if (row - 1 >= 0 && status[row - 1][col] == 0) {
//                    open(row - 1, col);
//                } else if (row + 1 < size && status[row + 1][col] == 0) {
//                    open(row + 1, col);
//                } else if (col - 1 >= 0 && status[row][col - 1] == 0) {
//                    open(row, col - 1);
//                } else if (col + 1 < size && status[row][col + 1] == 0) {
//                    open(row, col + 1);
//                }
//            }
        }
        else {
            throw new IndexOutOfBoundsException("row and col should be lower or equals to " + size);
        }
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
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
        // TODO: Fill in this method.
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
        // TODO: Fill in this method.
        return openedSites;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        for (int i = 0; i < size; i++) {
            if (lastFullElement != -1){
                if (uf.connected((i + size * (size - 1)), lastFullElement)) {
                    return true;
                }
            }
        }
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
