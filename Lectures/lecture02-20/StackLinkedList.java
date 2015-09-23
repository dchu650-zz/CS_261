package edu.ups.cs261;

import java.util.AbstractList;

public class StackLinkedList<E> //extends AbstractList<E> 
{

	private Node<E> head;
	private int size;
	
	public StackLinkedList() {
		head = null;
		size = 0;
	}

	public void push(E e)
	{
		Node<E> n = new Node<E>(e);
		n.next = head;
		head = n;
		size++;
	}

	public E pop()
	{
		E toReturn = head.data;
		head = head.next;
		size--;
		return toReturn;
	}
	
	//add
	private void add(int index, E e)
	{
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();

		if(index == 0)
		{
			Node<E> n = new Node<E>(e);
			n.next = head;
			head = n;
			size++;
		}
		else
		{
			//change the next of the before and after
			Node<E> ptr = head;
			for(int i=0; i<index-1; i++)
				ptr = ptr.next;

			//ptr.next is who I want to be
			Node<E> n = new Node<E>(e);
			n.next = ptr.next;
			ptr.next = n;
			size++;
		}
		
	}
	
	private boolean add(E e)
	{
		add(size,e);
		return true;
	}
	
	//remove
	private E remove(int index)
	{
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();

//		Node<E> ptr = head;
//		for(int i=0; i<index; i++)
//			ptr = ptr.next;

		//last node to point to guy in front of toRemove
		// guy in front: ptr.next
		// last guy = get(i-1);

		Node<E> ptr = head;
		for(int i=0; i<index-1; i++)
			ptr = ptr.next;
//		last guy: ptr
//		guy in front of toRemove: ptr.next.next

		E toReturn = ptr.next.data;
		ptr.next = ptr.next.next;
		size--;
		
		return toReturn;
		
//		Node<E> ptr = head;
//		Node<E> prev = null;
//		for(int i=0; i<index; i++)
//		{
//			prev = ptr;
//			ptr = ptr.next;
//		}

		
		
	}
	
	
	//get
	private E get(int index)
	{
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();

		Node<E> ptr = head;
		for(int i=0; i<index; i++)
			ptr = ptr.next;
				
		return ptr.data;
	}
	
	
	//set
	private E set(int index, E e)
	{
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();

		Node<E> ptr = head;
		for(int i=0; i<index; i++)
			ptr = ptr.next;
		
		ptr.data = e;
		return ptr.data;
	}
	
	//size
	public int size()
	{
		return size;
	}
	
	private static class Node<E> {

		private E data;
		private Node<E> next;
		
		public Node(E data) {
			this.data = data;
			next = null;
		}
	}
	
}
