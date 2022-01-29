package project3;

import java.util.EmptyStackException;

/**
 * A class using a doublylinkedlist as physical storage
 * to implement a stack
 * 
 * @author Michael Shu
 *
 * @version 3/31/21
 */

public class StackOfSpaces implements PossibleLocations {
	
	private DoublyLinkedList stack;
	
	/**
	 * Basic constructor creating a new list
	 */
	public StackOfSpaces()
	{
		stack = new DoublyLinkedList<Location>();
	}	

	/**
	  * Pushes a location onto a stack
	  * 
	  * @param s Location to be pushed onto the stack
	  * 
	  * @throws NullPointerException if param is null
	  * 
	  * @throws ClassCastException if param 
	  * isn't a location
	  */
	public void add(Location s) throws
	NullPointerException,ClassCastException
	{
		/* Need to check if parameter is null
		 * if it is a location, otherwise
		 * it'll mess up our location only list
		 */
		
		if(s==null)
			throw new NullPointerException();
		
		if(!(s instanceof Location))
			throw new ClassCastException();
		//Add by default adds to the end
		stack.add(s);
	}
	

	/**
	 * Pops the stop of the stack and returns it
	 * 
	 * @return location object at the top of the stack
	 * 
	 * @throws EmptyStackException if stack is empty
	 */
	public Location remove() throws EmptyStackException
	{
		if(isEmpty())
			throw new EmptyStackException();

		/* Create temporary location so
		 * when top is removed,
		 * data isn't lost and can be returned
		 */
		Location s = getTop();	
		stack.remove(size()-1);		
		
		return s;
	}
	
	/**
	 * Returns whether or not list is empty
	 * 
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty() 
	{
		return (stack.size()==0) ? true : false;
	}
	
	/**
	 * Returns size of list
	 * 
	 * @return size of list
	 */
	public int size()
	{
		return stack.size();
	}
	
	/**
	 * Retrieves the top location and returns it
	 * 
	 * @return Location at the top of the list
	 * 
	 * @throws EmptyStackException if list is empty
	 */
	public Location getTop() throws EmptyStackException
	{
		if(isEmpty())
			throw new EmptyStackException();
		
		return (Location)stack.get(stack.size()-1);
	}
	
	/**
	 * Returns a readable string of 
	 * each node in the stack
	 * 
	 * @return [(row, column), (row, column),etc]
	 */
	public String toString()
	{
		return stack.toString();
	}
}