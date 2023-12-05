public class UnionFind {
    /**
     * DO NOT DELETE OR MODIFY THIS, OTHERWISE THE TESTS WILL NOT PASS.
     * You can assume that we are only working with non-negative integers as the items
     * in our disjoint sets.
     */
    private int[] data;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        data = new int[N];
        // initialize every node as -1, which means they are in separate sets.
        for (int i = 0; i < N; i++) {
            data[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        int size;
        // if v is the root, return negative parent(v), which is the size
        if (parent(v) < 0) {
            size = -(parent(v));
        }
        // if v is not the root, then find its parent until find the root
        else {
            size = sizeOf(parent(v));
        }
        return size;
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        if (v > data.length) {
            throw new IllegalArgumentException("invalid input");
        }
        // if v is root, then data[v] is the size of the tree,
        // id v is not the root, data[v] is the parent of v.
        return data[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (find(v1) == find(v2)) {
            return true;
        }
        return false;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        int root;
        // if v is the root, return itself
        if (parent(v) < 0) {
            root = v;
        }
        // if v is not the root, return its parent until find the root
        else {
            root = find(parent(v));
        }
        return root;
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing a item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (v1 == v2) {
            return;
        }
        if (sizeOf(v1) > sizeOf(v2)) {
            data[find(v1)] += data[find(v2)];
            data[find(v2)] = find(v1);
        }
        else {
            data[find(v2)] += data[find(v1)];
            data[find(v1)] = find(v2);
        }
    }

    /**
     * DO NOT DELETE OR MODIFY THIS, OTHERWISE THE TESTS WILL NOT PASS.
     */
    public int[] returnData() {
        return data;
    }
}
