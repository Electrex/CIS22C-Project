package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ADT.BST;
import Modules.Product;

public class BSTtestCases {

	@Test
    public void CopyTest(){
        BST intBst = new BST();
        Product product = new Product("PC","AB123",440.0,10.0,"Dell","very nice");
        intBst.insert(product);

        BST intBstCopy = new BST(intBst);
        assertEquals(true, intBst.equals(intBstCopy));
        intBstCopy.remove(product);
        assertEquals(false, intBst.equals(intBstCopy));
        intBstCopy.insert(product);
        assertEquals(0, intBstCopy.getHeight());
        
    }
	
	@Test
    public void equals() {
        BST intBst = new BST();
        Product product = new Product("PC","AB123",440.0,10.0,"Dell","very nice");
        intBst.insert(product);

        BST intBstCopy = new BST(intBst);
        assertEquals(true, intBst.equals(intBstCopy));
        intBstCopy.remove(product);
        assertEquals(false, intBst.equals(intBstCopy));
        intBst.remove(product);
        assertEquals(true, intBst.equals(intBstCopy));
    }
	
	@Test
    public void findMax() {
        BST intBst = new BST();
        Product product = new Product("PC","AB123",440.0,10.0,"Dell","very nice");
        Product product1 = new Product("AC","AC",440.0,10.0,"HP","very nice");
        intBst.insert(product);
        intBst.insert(product1);

        assertEquals(product, intBst.findMax());
        intBst.remove(product);
        
        assertEquals(product1, intBst.findMax());
        intBst.remove(product1);
    }
	
	@Test
    public void insert() {
        BST intBst = new BST();
        Product product = new Product("PC","AB123",440.0,10.0,"Dell","very nice");
        Product product1 = new Product("AC","AC",440.0,10.0,"HP","very nice");
        //intBst.insert(product);
        //intBst.insert(product1);

        assertEquals(-1, intBst.getHeight());
        intBst.insert(product);
        assertEquals(0, intBst.getHeight());
        intBst.insert(product1);
        assertEquals(1, intBst.getHeight());
    }
	
	@Test
    public void search() {
        BST intBst = new BST();
        Product product = new Product("PC","AB123",440.0,10.0,"Dell","very nice");
        Product product1 = new Product("AC","AC",440.0,10.0,"HP","very nice");
        intBst.insert(product);
        intBst.insert(product1);

        assertEquals(false, intBst.search(new Product("P","B123",440.0,10.0,"Dell","very nice")));
        //intBst.remove(product);
        assertEquals(true, intBst.search(product));
        intBst.remove(product1);
        assertEquals(false, intBst.search(product1));
    }

}
