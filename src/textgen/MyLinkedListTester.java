/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 * 
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		// TODO: Add more tests here
		try {
			list1.remove(-1);
			fail("Check out of bounds");
		}
		catch(IndexOutOfBoundsException e) {}
		try {
			list1.remove(10);
			fail("Check out of bounds");
		}
		catch(IndexOutOfBoundsException e) {}
		
		int b = longerList.remove(2);
		assertEquals("Remove: check b is correct ", 2, b);
		assertEquals("Remove: check size is correct ", 9, longerList.size());
		assertEquals("Remove: check element 2 is correct ", (Integer)3, longerList.get(2));
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		try {
			shortList.add(null);
			fail("Check null");
		}
		catch(NullPointerException e){}
		
		shortList.add("ABC");
		assertEquals("Check last element","ABC",shortList.get(2));
		emptyList.add(1);
		assertEquals("Check last element",(Integer)1,emptyList.get(0));
		longerList.add(1);
		assertEquals("Check last element",(Integer)1,longerList.get(10));
		list1.add(31);
		assertEquals("Check last element",(Integer)31,list1.get(3));
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("Check size function",2,shortList.size());
		assertEquals("Check size function",0,emptyList.size());
		assertEquals("Check size function",10,longerList.size());
		assertEquals("Check size function",3,list1.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		try {
			shortList.add(1,null);
			fail("Check null");
		}
		catch(NullPointerException e){}
		
		try{
			shortList.add(-1,"ABC");
			fail("Check out of bounds");
		}
		catch(IndexOutOfBoundsException e){	}
		
		try{
			emptyList.add(1,2);
			fail("Check out of bounds");
		}
		catch(IndexOutOfBoundsException e){	}
		
		shortList.add(1,"ABC");
		assertEquals("Check added element","ABC",shortList.get(1));
		emptyList.add(0,2);
		assertEquals("Check added element",(Integer)2,emptyList.get(0));
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		try {
			shortList.set(1,null);
			fail("Check null");
		}
		catch(NullPointerException e){}
		
		try{
			shortList.set(-1,"ABC");
			fail("Check out of bounds");
		}
		catch(IndexOutOfBoundsException e){	}
		
		try {
			shortList.set(10,"TGH");
			fail("Check out of bound");
		}
		catch(IndexOutOfBoundsException e){}
		
		shortList.set(1, "test");
		assertEquals("Check element at 1st index","test",shortList.get(1));
		assertEquals("Check last element",2,shortList.size());
	}
	
	
	// TODO: Optionally add more test methods.
	
}
