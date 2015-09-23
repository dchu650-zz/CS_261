package edu.ups.cs261;

public class Node<E> {

	private E data;
	public Node<E> next;
	
	public Node(E data) {
		this.data = data;
		next = null;
	}
}
