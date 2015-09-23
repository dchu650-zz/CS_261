package edu.ups.cs261;

public class LinkedList<E> {

	private Node<E> head;
	private int size;
	
	public LinkedList() {
		head = null;
		size = 0;
	}

	//add  
	public void add(int index, E e)
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
	
	public void add(E e)
	{
		add(size,e);
	}
	
	//remove
	
	//get
	public E get(int index)
	{
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();

		Node<E> ptr = head;
		for(int i=0; i<index; i++)
			ptr = ptr.next;
				
		return ptr.data;
	}
	
	
	//set
	
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
