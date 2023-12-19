import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private int size = 0;

    private BSTNode root = null;

    private class BSTNode {
        K k;
        V v;
        BSTNode leftChild;
        BSTNode rightChild;

        // constructor for each node
        private BSTNode(K key, V value) {
            k = key;
            v = value;
            leftChild = null;
            rightChild = null;
        }
    }



    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new BSTNode(key, value);
            root.leftChild = null;
            root.rightChild = null;
            size += 1;
        }
        else {
            putOrDeeper(root, key, value);
        }
    }

    // since the BST have log n deep, the total space cost in stack will be log n.
    private void putOrDeeper(BSTNode cur, K key, V value) {
        // new key less than cur key, go left
        if (key.compareTo(cur.k) < 0) {
            if (cur.leftChild == null) {
                cur.leftChild = new BSTNode(key, value);
                size += 1;
            }
            else {
                putOrDeeper(cur.leftChild, key, value);
            }
        }
        // new key greater than cur key, go right
        else if (key.compareTo(cur.k) > 0){
            if (cur.rightChild == null) {
                cur.rightChild = new BSTNode(key, value);
                size += 1;
            }
            else {
                putOrDeeper(cur.rightChild, key, value);
            }
        }
        // new key == cur key, replace current value
        else {
            cur.v = value;
        }
    }

    @Override
    public V get(K key) {
        if (root == null) {
            return null;
        }
        V returnValue = null;
        BSTNode returnNode = getOrDeeper(root, key);
        if (returnNode != null) {
            returnValue = returnNode.v;
        }
        return returnValue;
    }

    private BSTNode getOrDeeper(BSTNode cur, K key) {
        BSTNode returnNode = null;
        if (key.compareTo(cur.k) < 0) {
            if (cur.leftChild == null) {
                returnNode =  new BSTNode(key, null);
            }
            else {
                returnNode = getOrDeeper(cur.leftChild, key);
            }
        }
        // new key greater than cur key, go right
        else if (key.compareTo(cur.k) > 0){
            if (cur.rightChild == null) {
                returnNode = new BSTNode(key, null);
            }
            else {
                returnNode = getOrDeeper(cur.rightChild, key);
            }
        }
        else if (key.equals(cur.k)){
            returnNode = cur;
            return returnNode;
        }
        return returnNode;
    }

    @Override
    public boolean containsKey(K key) {
        if (root == null) {
            return false;
        }
        return containsOrDeeper(root, key);
    }

    private boolean containsOrDeeper (BSTNode cur, K key) {
        if (key.compareTo(cur.k) < 0) {
            if (cur.leftChild == null) {
                return false;
            }
            else {
                containsOrDeeper(cur.leftChild, key);
            }
        }
        else if (key.compareTo(cur.k) > 0) {
            if (cur.rightChild == null) {
                return false;
            }
            else {
                containsOrDeeper(cur.rightChild, key);
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }
}
