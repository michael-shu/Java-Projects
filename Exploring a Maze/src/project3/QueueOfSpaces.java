
package project3;

import java.util.NoSuchElementException;

/**
 * A class using a doublylinkedlist as physical storage
 * to implement a queue
 * 
 * @author Michael Shu
 *
 * @version 3.31.21
 */

public class QueueOfSpaces implements PossibleLocations {
	
	DoublyLinkedList queue;
	
	/**
	 * Basic constructor creating a new list
	 */
	public QueueOfSpaces()
	{
		queue = new DoublyLinkedList<Location>();
	}
	
	/**
	 * Method adds location s to the back of the queue
	 * 
	 * @param s location to be added to the queue
	 * 
	 * @throws NullPointerException if param is null
	 * 
	 * @throws ClassCastException if param is not a 
	 * location object
	 */
	public void add(Location s) throws
		NullPointerException, ClassCastException
	{
		if(s==null)
			throw new NullPointerException();
		
		if(!(s instanceof Location))
			throw new ClassCastException();
		
		queue.add(s,0);
	}

	/**
	 * Method dequeues the front of the queue off
	 * 
	 * @throws NoSuchElementException if queue is empty
	 */
	public Location remove() throws
		NoSuchElementException
	{
		if(isEmpty())
			throw new NoSuchElementException();
		
		Location temp = getFront();
		queue.remove(size()-1);
		
		return temp;
	}
	
	/**
	 * Method gets the current size of the queue
	 * 
	 * @return size of the queue
	 */
	public int size()
	{
		return queue.size();
	}
	
	/**
	 * Figures out if list is empty or not
	 * 
	 * @return true if empty, false if not
	 */
	public boolean isEmpty()
	{
		return (size()==0) ? true: false;
	}
	
	/**
	 * Retrieves front of queue
	 * 
	 * @return location at the front of the queue
	 * 
	 * @throws NoSuchElementException if queue is empty
	 */
	public Location getFront() throws
		NoSuchElementException
	{
		if(isEmpty())
			throw new NoSuchElementException();
		
		return (Location)queue.get(queue.size()-1);
	}
	
	/**
	 * Retrieves back of queue
	 * 
	 * @return location at the back of the queue
	 * 
	 * @throws NoSuchElementException if queue is empty
	 */
	public Location getBack() throws
		NoSuchElementException
	{
		if(isEmpty())
			throw new NoSuchElementException();
		
		return(Location)queue.get(0);
	}
	
	/**
	 * Prints a readable string of data points of queue
	 * 
	 * @return [(row, column) , (row, column)..etc]
	 */
	public String toString()
	{
		return queue.toString();
	}
	
}