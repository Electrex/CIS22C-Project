package ADT;
//Yusuf

import java.util.ArrayList;
import java.util.NoSuchElementException;

import Modules.Product;

public class BST{

    private class Node {
        private Product data;
        private Node left;
        private Node right;

        public Node(Product data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    private Node root;
    private ArrayList<Product> list;

    /***CONSTRUCTORS***/

    /**
     * Default constructor for BST
     * sets root to null
     */
    public BST() {
        root = null;
        list = new ArrayList<Product>();
    }

    /**
     * Copy constructor for BSProduct
     * @param bst the BSProduct to make
     * a copy of
     */
    public BST(BST bst) {
        if (bst.root == null)
        {
            root = null;
        	list = new ArrayList<Product>();
        }
        else {
            copyHelper(bst.root);
            this.list = bst.list;
        }
    }

    /**
     * Helper method for copy constructor
     * @param node the node containing
     * data to copy
     */
    private void copyHelper(Node node) {
        if (node != null){
            insert(node.data);
            copyHelper(node.left);
            copyHelper(node.right);
        }
    }

    /***ACCESSORS***/

    /**
     * Returns the data stored in the root
     * @precondition !isEmpty()
     * @return the data stored in the root
     * @throws NoSuchElementException when
     * preconditon is violated
     */
    public Product getRoot() throws NoSuchElementException{
        if (isEmpty())
            throw new NoSuchElementException("getRoot(): Tree is empty");
        else
            return root.data;
    }

    /**
     * Determines whether the tree is empty
     * @return whether the tree is empty
     */
    public boolean isEmpty() {
        if (root == null)
            return true;
        else
            return false;
    }

    /**
     * Returns the current size of the
     * tree (number of nodes)
     * @return the size of the tree
     */
    public int getSize() {
        return getSize(root);
    }

    /**
     * Helper method for the getSize method
     * @param node the current node to count
     * @return the size of the tree
     */
    private int getSize(Node node) {
        if (node == null)
            return 0;
        else {
            return 1  + getSize(node.left) + getSize(node.right);
        }
    }

    /**
     * Returns the height of tree by
     * counting edges.
     * @return the height of the tree
     */
    public int getHeight() {
        if (isEmpty())
            return -1;
        else {
            return getHeight(root);
        }
    }

    /**
     * Helper method for getHeight method
     * @param node the current
     * node whose height to count
     * @return the height of the tree
     */
    private int getHeight(Node node) {
        if (node == null)
            return -1;
        int lHeight = getHeight(node.left);
        int rHeight = getHeight(node.right);
        if (lHeight > rHeight)
            return lHeight + 1;
        else
            return rHeight + 1;
    }

    /**
     * Returns the smallest value in the tree
     * @precondition !isEmpty()
     * @return the smallest value in the tree
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public Product findMin() throws NoSuchElementException{
        if (isEmpty())
            throw new NoSuchElementException("findMin(): Tree is empty");
        else
            return findMin(root);
    }

    /**
     * Helper method to findMin method
     * @param node the current node to check
     * if it is the smallest
     * @return the smallest value in the tree
     */
    private Product findMin(Node node) {
        if (node.left != null)
            return findMin(node.left);
        else
            return node.data;
    }

    /**
     * Returns the largest value in the tree
     * @precondition !isEmpty()
     * @return the largest value in the tree
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public Product findMax() throws NoSuchElementException{
        if (isEmpty())
            throw new NoSuchElementException("findMax(): Tree is empty");
        else
            return findMax(root);
    }

    /**
     * Helper method to findMax method
     * @param node the current node to check
     * if it is the largest
     * @return the largest value in the tree
     */
    private Product findMax(Node node) {
        if (node.right != null)
            return findMax(node.right);
        else
            return node.data;
    }

    /**
     * Searches for a specified value
     * in the tree
     * @param data the value to search for
     * @return whether the value is stored
     * in the tree
     */
    public boolean search(Product data) {
        if (isEmpty())
            return false;
        else
            return search(data, root);
    }

    /**
     * Helper method for the search method
     * @param data the data to search for
     * @param node the current node to check
     * @return whether the data is stored
     * in the tree
     */
    private boolean search(Product data, Node node) {
        if (data.compareTo(node.data) == 0)
            return true;
        if (data.compareTo(node.data) < 0) {
            if (node.left == null)
                return false;
            else
                return search(data, node.left);
        } else {
            if (node.right == null)
                return false;
            else
                return search(data, node.right);
        }
    }

    /**
     * Searches for a specified value
     * in the tree
     * @param data the value to search for
     * @return whether the value is stored
     * in the tree
     */
    public boolean searchByPrimary(Product data) {
        if (isEmpty())
            return false;
        else
            return searchByPrimary(data, root);
    }

    /**
     * Helper method for the search method
     * @param data the data to search for
     * @param node the current node to check
     * @return whether the data is stored
     * in the tree
     */
    private boolean searchByPrimary(Product data, Node node) {
        if (data.compareToPrimary(node.data) == 0)
            return true;
        if (data.compareToPrimary(node.data) < 0) {
            if (node.left == null)
                return false;
            else
                return searchByPrimary(data, node.left);
        } else {
            if (node.right == null)
                return false;
            else
                return searchByPrimary(data, node.right);
        }
    }

    /**
     * Searches for a specified value
     * in the tree
     * @param data the value to search for
     * @return whether the value is stored
     * in the tree
     */
    public boolean searchBySecondary(Product data) {
        if (isEmpty())
            return false;
        else
            return searchBySecondary(data, root);
    }

    /**
     * Helper method for the search method
     * @param data the data to search for
     * @param node the current node to check
     * @return whether the data is stored
     * in the tree
     */
    private boolean searchBySecondary(Product data, Node node) {
        if (data.compareToSecondary(node.data) == 0)
            return true;
        if (data.compareToSecondary(node.data) < 0) {
            if (node.left == null)
                return false;
            else
                return searchBySecondary(data, node.left);
        } else {
            if (node.right == null)
                return false;
            else
                return searchBySecondary(data, node.right);
        }
    }


    /**
     * Determines whether two trees store
     * identical data in the same structural
     * position in the tree
     * @param o another Object
     * @return whether the two trees are equal
     */
    @Override public boolean equals(Object o) {
        return equals(root, ((BST)o).root);
    }

    /**
     * Helper method for the equals method
     * @param node1 the node of the first bst
     * @param node2 the node of the second bst
     * @return whether the two trees contain
     * identical data stored in the same structural
     * position inside the trees
     */
    private boolean equals(Node node1, Node node2) {
        if (node1 == null && node2 == null)
            return true;

        if (node1 != null && node2 != null) {
            return ((node1.data == node2.data)
                    && equals(node1.left, node2.left)
                    && equals(node1.right, node2.right));
        }

        return false;
    }

    /***MUTATORS***/

    /**
     * Inserts a new node in the tree
     * @param data the data to insert
     */
    public void insert(Product data) {
        if (root == null) {
            root = new Node(data);
        } else {
            insert(data, root);
        }
    }

    /**
     * Helper method to insert
     * Inserts a new value in the tree
     * @param data the data to insert
     * @param node the current node in the
     * search for the correct location
     * in which to insert
     */
    private void insert(Product data, Node node) {
        if (data.compareTo(node.data) <= 0) {
            if (node.left == null)
                node.left = new Node(data);
            else
                insert(data, node.left);
        } else {
            if (node.right == null)
                node.right = new Node(data);
            else
                insert(data, node.right);
        }
    }
    /**
     * Removes a value from the BST
     * @param data the value to remove
     * @precondition !isEmpty()
     * @precondition the data is located in the tree
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public void remove(Product data) throws NoSuchElementException{
        if (isEmpty() || !search(data))
            throw new NoSuchElementException("remove(): Element is not found");
        else
            root = remove(data, root);
    }

    /**
     * Helper method to the remove method
     * @param data the data to remove
     * @param node the current node
     * @return an updated reference variable
     */
    public Node remove(Product data, Node node)
    {
        if(node == null)
        {
            return node;
        }
        else if(data.compareTo(node.data) < 0)
        {
            node.left = remove(data, node.left);
        }
        else if(data.compareTo(node.data) > 0)
        {
            node.right = remove(data, node.right);
        }
        else
        if(node.left == null && node.right == null)
        {
            node = null;
        }
        else if(node.left != null && node.right == null)
        {
            node = node.left;
        }
        else if(node.left == null && node.right != null)
        {
            node = node.right;
        }
        else
        {
            node.data = findMin(node.right);
            node.right = remove(node.data, node.right);
        }
        return node;
    }

    /***ADDITIONAL OPERATIONS***/

    public void sortByPrimary()
    {
        list.clear();
        inOrderStore(root);
        bubblesortByPrimary();
    }

    private void bubblesortByPrimary()
    {
        int i, j;
        Product temp;
        int n = list.size();
        boolean swapped;
        for (i = 0; i < n - 1; i++)
        {
            swapped = false;
            for (j = 0; j < n - i - 1; j++)
            {
                if (list.get(j).compareToPrimary(list.get(j + 1)) > 0)
                {
                    // swap arr[j] and arr[j+1]
                    temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }

            // IF no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }
    }

    public void sortBySecondary()
    {
        list.clear();
        inOrderStore(root);
        bubblesortBySecondary();
    }

    private void bubblesortBySecondary()
    {
        int i, j;
        Product temp;
        int n = list.size();
        boolean swapped;
        for (i = 0; i < n - 1; i++)
        {
            swapped = false;
            for (j = 0; j < n - i - 1; j++)
            {
                if (list.get(j).compareToSecondary(list.get(j + 1)) > 0)
                {
                    // swap arr[j] and arr[j+1]
                    temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }

            // IF no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }
    }

    /**
     * Helper method to inOrderPrint method
     * Prints the data in sorted order
     * to the console
     */
    private void inOrderStore(Node node) {
        if (node != null){
            inOrderStore(node.left);
            list.add(node.data);
            inOrderStore(node.right);
        }
    }

    public ArrayList<Product> getProducts() {
        return list;
    }

}