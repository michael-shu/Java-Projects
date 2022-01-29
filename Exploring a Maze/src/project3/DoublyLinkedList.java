package project3;

import java.util.Iterator;
import java.util.Objects;

/**
 * This class is a basic implementation of a doubly linked
 * list that will be used to solve a maze
 * 
 * @author Michael Shu
 * 
 * @version 3/31/21
 */
	
public class DoublyLinkedList<E> implements Iterable<E>
{
	//Simple node class
 public class Node<E> {

	private Node<E> next;
	private Node<E> prev;
	private E data;
	/* All values will be set to null by
	* default, until the "Mazerunner"
	* has looked at them and set
	* the node values.
	*/
		
	/**
	* Default constructor setting all
	* values to null
	*/
	public Node()
	{
		next = null;
		prev = null;
		data = null;
	}
		
	/**
	 * Constructor that inputs data,
	 * setting next and prev to null
	 * 
	 * @param data what the node will store
	 */
	 public Node(E data)
	 {
		 this(data,null,null);
	 }
		
	/**
	 * Constructor that takes two given nodes
	 * and creates a new one inbetween
	 * 
	 * @param data what the node will store
	 * 
	 * @param next reference of the
	 * node in front
	 * 
	 * @param prev reference of the node behind
	 */
	 public Node(E data,Node<E>next,Node<E>prev)
	 {
		this.data = data;
		this.next = next;
		this.prev = prev;
	 }
 }
	

	private Node<E> head;
	private Node<E> tail;
	private int length;
	
	/**
	 * Default constructor sets head and tail of list
	 * to null, and length of list to 0
	 */
	public DoublyLinkedList()
	{
		head = null;
		tail = null;
		length = 0;
	}
	
	/**
	 * Adds an object e to the end of the list
	 * 
	 * @param object e to be added to the list
	 * 
	 * @return true if element is added to the list,
	 * false, otherwise
	 * 
	 * @throws NullPointerException if null param
	 * is passed
	 */
	public boolean add(E e) throws
	ClassCastException, NullPointerException
	{
		if(e==null)
			return false;

		Node<E> node = new Node<E>(e);
		
		/* if there are no elements in the
		 * list,head and tail need to both 
		 * be set to the first node
		 */
		if(head==null)
		{
			head = node;
			tail = node;
		}
		else
		{
			node.prev = tail;
			tail.next = node;
			tail = node;
		}
		length = length + 1;
		return true;
	}
	
	/**
	 * Specifically adds object e to the head
	 * of the list
	 * 
	 * @param e object being added to the list
	 * 
	 * @return true if e is added, false otherwise
	 * 
	 * @throws NullPointerException if param is null
	 */
	public boolean addHead(E e)
	{
		if(e==null)
			return false;
		
		Node new_node = new Node(e);
		if(length==0)
		{
			head = new_node;
		}
		else
		{
			new_node.next = head;
	        head = new_node;
	        head.next.prev = head;
		}
		length = length + 1;
		return true;
	}
	
	/**
	 * Slots in object e at whatever index is designated
	 * by pos
	 * 
	 * @param e object to be added
	 * 
	 * @param pos position at which the object 
	 * should be inserted
	 * 
	 * @return true if e is added, otherwise false
	 * 
	 * @throws IndexOutOfBoundsException if position
	 * given is negative or greater than the size
	 * of the linked list
	 */
	public boolean add(E e, int pos) throws
	  ClassCastException, IndexOutOfBoundsException
	{
		if(e==null)
		  return false;
		if(pos>length)
		  throw new IndexOutOfBoundsException();
		if(pos<0)
		  throw new IndexOutOfBoundsException();
		
		/* If pos==length, then the user 
		 * just wants to add the node 
		 * to the end
		 */
		if(pos==length)
		{
			add(e);
			return true;
		}
		/* If pos==0, then the user 
		 * just wants to add
		 * the node to the head
		 */
		if(pos==0)
		{	
			addHead(e);
			return true;
		}
		
		/* Otherwise, use a temporary node 
		 * as an iterator and start from 
		 * the head, going until you 
		 * reach position
		 */
		Node new_Node = new Node(e);
		Node iterator = head;
		for(int i = 0;i<pos;i++)
		{
			iterator = iterator.next;
		}
		
		new_Node.next = iterator;
		iterator.prev.next = new_Node;
		length = length + 1;
		return true;
		
	}
	
	/**
	 * Removes all the nodes from a list and deletes
	 * them so they don't take up space, creating an 
	 * empty list 
	 */
	public void clear()
	{
		/* for loop creates a temporary node 
		 * (next) in order to properly 
		 * delete the current node, 
		 * and still keep the next reference.
		 */
		for (Node<E> x = head; x != null; ) 
		{
            Node<E> next = x.next;
            x.data = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
		/* Have to set head and tail to null as
		 * well since head/tail are references
		 * to the nodes, because if you don't
		 * they'll just sit there as nodes
		 * with null data/pointers
		 */
		head = tail = null;
		length = 0;
	}
	
	/**
	 * checks if the list contains object o
	 * 
	 * @param o object being searched for
	 * 
	 * @return true if the list contains at least one
	 * equivalent object, false otherwise
	 */
	public boolean contains(Object o)
	{
		if(o==null)
			return false;
		
		Node iterator = head;
		
		/* Have to test that there are even
		 * things in the list in the first place
		 */
		if(isEmpty())
			return false;
		
		//Test from head to the 2nd to last node
		while(iterator.next!=null)
		{
			if(Objects.equals(o, iterator.data))
					return true;
			
			iterator = iterator.next;			
		}
		
		/* have to test for tail individually
		 * since tail.next is automatically null
		 */
		if(Objects.equals(o, tail.data))
			return true;
		
		return false;
	}
	
	/**
	 * Tests for list equivalence
	 * 
	 * @param o list being compared with
	 * 
	 * @return true if the lists are both
	 * the same length with the same exact
	 * elements in the same exact indices
	 * in each spot, otherwise false
	 */
	public boolean equals(Object o)
	{
		
		/* Null, same object, and if its an
		 * instance of doubly linked list all
		 * should be checked first
		 */
		if(o==null)
			return false;
		
		if(o==this)
			return true;

		if(!(o instanceof DoublyLinkedList))
			return false;
		
		DoublyLinkedList d = (DoublyLinkedList)o;
		
		
		/* Makes equals more efficient by checking
		 * length at the beginning, because
		 * otherwise we'd have to go through the
		 * entirety of the shorter list
		 */
		if(this.length!= d.length)
			return false;
		
		//Two empty lists are equivalent
		if(this.length==0&&d.length==0)
			return true;
		
		Node iterator1 = this.head;
		Node iterator2 = d.head;
		
		/*Actual loop to check for equivalence
		 * at each corresponding node
		 */
        while(iterator1.next!=null&&iterator2.next!=null)
		{
          if(!(iterator1.data.equals(iterator2.data)))
			   return false;		
		
			iterator1 = iterator1.next;
			iterator2 = iterator2.next;
		}
		
		//again test for tail individually
		if(!(tail.data.equals(d.tail.data)))
			return false;
		
		return true;
		
	}
	
	/**
	 * gets a node at a certain position in the list and
	 * returns the data of the node
	 * 
	 * @param pos the index we are searching for
	 * 
	 * @return the data of the node at that index. 
	 * If index is out of bounds, returns null
	 */
	public E get(int pos)
	{
		if(pos>=length||pos<0)
			return null;
		
		
		Node iterator = head;
			for(int i = 0; i<pos;i++)
			{
				iterator = iterator.next;
			}
		
		return (E)iterator.data;
	}
	
	/**
	 * checks if list is empty
	 * 
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty()
	{
		if(length==0)
			return true;
		return false;
	}
	
	/**
	 * Gets a node at a certain position in the list and
	 * returns the data of the node
	 * 
	 * @param pos the index we are searching for
	 * 
	 * @return the data of the node at that index.
	 * If index is out of bounds, returns null.
	 */
	public int indexOf(Object o)
	{
		if(o==null)
			return -1;
		if(isEmpty())
			return -1;
		
		int i = 0;
		Node iterator = head;
		
		while(iterator.next!=null)
		{
			if(Objects.equals(o,iterator.data))
				return i;
			
			iterator = iterator.next;
			i++;
		}
		if(Objects.equals(o,tail.data))
			return i;
		
		return -1;
		
	}
	
	/**
	 * If param exists in the list, method
	 * removes it from the list
	 * 
	 * @param o object being searched for and removed
	 * 
	 * @return true if object is removed,
	 * false otherwise
	 * 
	 * @throws NullPointerException if param is null
	 * 
	 * @throws ClassCastException if param is not
	 * the same type as rest of the list
	 */
	public boolean remove(Object o) throws
	NullPointerException, ClassCastException
    {
		if(length==0)
			return false;
		
		if(o==null)
			return false;
	   if(!(o.getClass().equals(head.data.getClass())))
			return false;
			
		
		Node iterator = head;
		/* If data equals head,
		 * then you have two situations.
		 * 1.There are other elements in the list
		 * 
		 * If theres other elements, you need
		 * to reassign head to head.next and
		 * then make the new head.prev = to null.
		 * 
		 * 2.The list only consists of head. 
		 * 
		 * If it's only head, then you can't
		 * make the new head.prev equal to null. 
		 * It creates an null pointer error. 
		 * So, you need to set head to null, 
		 * set length = 0 without any 
		 * reassignment of pointers
		*/
        if(head.data.equals(o))
        {	
            if(length==1)
            {
                head = null;
                length = 0;
                return true;
            }
            else
            {
                head = head.next;
                head.prev = null;
                length = length-1;
                return true;
            }
        }
		
		//Tests for the rest of the list
        while(iterator.next!=null)
        {
            if(iterator.next.data.equals(o))
            {
                /* Need to check for special
                 * tail case,
                 * otherwise .next.next 
                 * will call a null
                 * value and throw 
                 * a nullpointerexception
                 */
                if(iterator.next.data.equals(tail.data))
                {
                    iterator.next = null;
                    tail = iterator;
                }
                else 
                {
                  iterator.next = iterator.next.next;
                  iterator.next.prev = iterator;
                }
                length = length -1;
                return true;
            }
            /* If equivalence is not found, 
             * keep going
             */
            iterator = iterator.next;
         }
     /* If conditional statement doesn't occur, 
      * return false
      */
       return false;
    }
	
	/**
	 * Removes element in list based off of an index
	 * 
	 * @param pos index of the element that
	 * is to be removed
	 * 
	 * @return data of the element that is removed
	 * 
	 * @throws IndexOutOfBoundsException if 
	 * pos is negative or larger >= length
	 */
	public E remove(int pos) throws 
		IndexOutOfBoundsException
	{
        if(pos>=length||pos<0)
          throw new IndexOutOfBoundsException();
		
		/* If remove is at the head or tail, 
		 * it's a special case since you have 
		 * to redeclare what is head/tail
		 */
		if(pos==0)
		{
			Node temp = new Node(head.data);
			head = head.next;
			length = length-1;
			return (E) temp.data;
		}
		//Tail coding
		if(pos==length-1)
		{
			Node temp = new Node(tail.data);
			tail = tail.prev;
			tail.next = null;
			length = length-1;
			return (E) temp.data;
		}
		
		/* Every other case besides head/tail. The 
		 * iterator stops right before the position
		 * you want to remove, and essentially skips 
		 * over it by making .next into .next.next.
		 * Before doing that, it stores the data in 
		 * temp so the removed 
		 * node data isn't lost.
		 */
		Node iterator = head;
		for(int i = 0; i<pos-1;i++)
		{
			iterator = iterator.next;
				
		}			
	
		Node<E> temp = new Node(iterator.next.data);
		
		iterator.next = iterator.next.next;
		iterator.next.prev = iterator;
	
		length = length-1;		
		return temp.data;		
	}
	
	/**
	 * Gives number of entries in the list
	 * 
	 * @return number of entries in the list
	 */
	public int size()
	{
		return length;
	}
	
	/**
	 * Returns data of the nodes of the list into a 
	 * readable String 
	 * 
	 * @return [entry1, entry2, entry3, entry4, ...]
	 */
	public String toString()
	{
		if(length==0)
			return "Nothing in the list";
		
		Node iterator = head;
		String s = "[";
	  //Main loop from head to 2nd to last element
	  while(iterator.next!=null)
	  {
        s = s + String.valueOf(iterator.data) + ", ";
        iterator = iterator.next;
	  }
		//Specific tail coding
		s = s + tail.data.toString() + "]";
		return s;
	}
	
	/**
	 * Returns a new iterator, but keeps implementation
	 * of the iterator private in different class
	 * 
	 * @return new list iterator
	 */
	 public Iterator<E> iterator() {
	        return new iterate();
	    }
	
	private class iterate implements Iterator<E> 
	{
		
        private Node<E> current = head;
        
        public boolean hasNext() 
        {
            return current != null;
        }
        
        public E next()
        {
            E tmp = current.data;
            current = current.next;
            return tmp;
        }
    }		
}	