/**
 * A class that represents a Binary Tree, defined with Nodes
 */
public class BinaryTree<E>
{
	private static final String SPACE_DELIM = "  ";

	protected Node<E> root;
		

	public BinaryTree(E data, BinaryTree<E> leftBranch, BinaryTree<E> rightBranch)
	{
		root = new Node<E>(data); //take the given data, and make it into the root
		if(leftBranch != null)
			root.left = leftBranch.root; //grab the root from the given trees, make them the child
		if(rightBranch != null)
			root.right = rightBranch.root;
	}

	public BinaryTree(E data)
	{
		this(data, null, null); // call previous method
	}

	public BinaryTree()
	{
		this.root = null;
	}
	
	protected BinaryTree(Node<E> root)
	{
		this.root = root;
	}	
	
	
	
	public BinaryTree<E> getLeftSubTree()
	{
		if(root != null)
			return new BinaryTree<E>(root.left); //make new binary tree out of root
		return new BinaryTree<E>();
	}
	
	public BinaryTree<E> getRightSubTree()
	{
		if(root != null)
			return new BinaryTree<E>(root.right);
		return new BinaryTree<E>();
	}
	
	public E getData()
	{
		return root.data;
	}
	
	public void printPreOrder()
	{
		printPreOrder(root); //call a helper (doesn't need separate name)
		System.out.println();
	}
	public void printPostOrder()
	{
		printPostOrder(root);
		System.out.println();
	}
	public void printInOrder()
	{
		printInOrder(root);
		System.out.println();
	}

	private void printPreOrder(Node<E> localRoot)
	{
		System.out.print(localRoot.data+", ");
		if(localRoot.left != null)
			printPreOrder(localRoot.left);
		if(localRoot.right != null)
			printPreOrder(localRoot.right);
	}
	private void printPostOrder(Node<E> localRoot)
	{
		if(localRoot.left != null)
			printPostOrder(localRoot.left);
		if(localRoot.right != null)
			printPostOrder(localRoot.right);
		System.out.print(localRoot.data+", ");
	}
	private void printInOrder(Node<E> localRoot)
	{
		if(localRoot.left != null)
			printInOrder(localRoot.left);
		System.out.print(localRoot.data+", ");
		if(localRoot.right != null)
			printInOrder(localRoot.right);
	}

	
	
	public String toString()
	{
		return root.toStringInOrder("");
	}
	
	
	protected static class Node<E>
	{
		protected E data;
		protected Node<E> left; //left child
		protected Node<E> right; //right child
		
		public Node(E data)
		{
			this.data = data;
		}
		
		public String toString()
		{
			return data.toString();
		}
		
		
		
		
		private String toStringPreOrder(String currentSpace)
		{
			String s = "";
		
			s += currentSpace + data + "\n";

			if(left != null)
				s += left.toStringPreOrder(currentSpace+SPACE_DELIM);
//			else
//				s += currentSpace + SPACE_DELIM + "null\n";
			
			if(right != null)
				s += right.toStringPreOrder(currentSpace+SPACE_DELIM);
//			else 
//				s += currentSpace + SPACE_DELIM + "null\n";
				
			return s;
		}
		
		private String toStringPostOrder(String currentSpace)
		{
			String s = "";
			
			if(left != null)
				s += left.toStringPostOrder(currentSpace+SPACE_DELIM);
//			else
//				s += currentSpace + SPACE_DELIM + "null\n";
			
			if(right != null)
				s += right.toStringPostOrder(currentSpace+SPACE_DELIM);
//			else 
//				s += currentSpace + SPACE_DELIM + "null\n";

			s += currentSpace + data + "\n";
			
			return s;
		}
	
		private String toStringInOrder(String currentSpace)
		{
			String s = "";
			
			if(left != null)
				s += left.toStringInOrder(currentSpace+SPACE_DELIM);
//			else
//				s += currentSpace + SPACE_DELIM + "null\n";

			s += currentSpace + data + "\n";
			
			if(right != null)
				s += right.toStringInOrder(currentSpace+SPACE_DELIM);
//			else 
//				s += currentSpace + SPACE_DELIM + "null\n";

			return s;
		}
	}

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		BinaryTree<Integer> tree = new BinaryTree<Integer>(30,
			new BinaryTree<Integer>(15, 
				new BinaryTree<Integer>(4),
				new BinaryTree<Integer>(20,
					new BinaryTree<Integer>(18,
						new BinaryTree<Integer>(),
						new BinaryTree<Integer>(19)
					),
					new BinaryTree<Integer>()
				)
			),
			new BinaryTree<Integer>(35, 
				new BinaryTree<Integer>(32),
				new BinaryTree<Integer>(38)
			)
		);

		System.out.println(tree);
		tree.printPreOrder();
		tree.printPostOrder();
		tree.printInOrder();
	}

}
