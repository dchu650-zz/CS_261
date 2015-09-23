package edu.ups.cs261;

import java.util.List;
import java.util.AbstractList;

/**
 * A from-scratch implementation of the ArrayList class
 * @author C261, Sp13
 *
 * @param <E> an Object type

 * need to implement to make all list methods work:
	  add(int, E)	
	  get(int)
	  remove(int)
	  set(int,E)
	  size()
 *
 */
public class StackArrayList<E>// extends AbstractList //means we implement List because our parent does
{
	private E[] theArray;
	private static final int INITIAL_CAPACITY = 10;
	private int size;

	
	public StackArrayList() {
		theArray = (E[])(new Object[INITIAL_CAPACITY]);
		size = 0;
	}

	public void push(E e)
	{
		add(0, e);
	}

	public E pop()
	{
		return remove(0);
	}
	
	public boolean isEmpty()
	{
		return size == 0;
	}

	public E peek()
	{
		return get(0);
	}
	
	
	
	
	
	private int size()
	{
		return size;
	}
	
	
	private boolean add(Object o) //taking Object as a param to match interface (?)
	{
		
		if(size == theArray.length)
			reallocate();
			
		theArray[size] = (E)o; //cast to an object; may throw cast exception
		size++;
		
		return true;
	}

	private boolean add(int index, Object o) //taking Object as a param to match interface (?)
	{		
		if(index < 0 || index > size)
			throw new ArrayIndexOutOfBoundsException();

		if(size == theArray.length)
			reallocate();

		for(int i=size; i> index; i--)
		{
			theArray[i] = theArray[i-1]; 
		}
		theArray[index] = (E)o;
		size++;
		
		return true;
	}

	
	private E get(int index)
	{
		if(index < 0 || index >= size)
			throw new ArrayIndexOutOfBoundsException();
		
		return theArray[index];		
	}

	private E remove(int index)
	{
		if(index < 0 || index >= size)
			throw new ArrayIndexOutOfBoundsException();

		E toReturn = theArray[index];
		
		for(int i=index+1; i<size; i++)
		{
			theArray[i-1] = theArray[i]; 
		}
		theArray[size-1] = null;

		size--;
		
		return toReturn;
		
	}
	
	private void clear()
	{
		theArray = (E[])(new Object[INITIAL_CAPACITY]);
		size = 0;
	}
	
	private void reallocate()
	{
		E[] newArray = (E[])(new Object[size*2]);
		for(int i=0; i<theArray.length; i++)
		{
			newArray[i] = theArray[i];
		}

		theArray = newArray;
	}
	
	
}
