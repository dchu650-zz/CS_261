/**
 * A class that represents a Binary Tree, defined recursively
 */
public class BinaryTreeRec<E>
{
	private static final String SPACE_DELIM = "  ";

	protected E data;
	protected BinaryTreeRec<E> left;
	protected BinaryTreeRec<E> right;

	public BinaryTreeRec(E data, BinaryTreeRec<E> leftBranch, BinaryTreeRec<E> rightBranch)
	{
		this.data = data;
		this.left = leftBranch; //just take the branch
		this.right = rightBranch;
	}

	public BinaryTreeRec(E data)
	{
		this(data,null,null);
	}

	public BinaryTreeRec()
	{
		this(null);
	}
	
	public BinaryTreeRec<E> getLeftSubTree()
	{
		return left;
	}
	
	public BinaryTreeRec<E> getRightSubTree()
	{
		return right;
	}
	
	public E getData()
	{
		return data;
	}
	
	//print using pre-order traversal
	//launcher method!
	public void printPreOrder()
	{
		printPreOrderHelper();
		System.out.println();
	}
	//launcher for post
	public void printPostOrder()
	{
		printPostOrderHelper();
		System.out.println();
	}
	public void printInOrder()
	{
		printInOrderHelper();
		System.out.println();
	}

	//recursive method for preorder traversal
	private void printPreOrderHelper()
	{
		System.out.print(data+", "); //first do me
		if(left != null)
			left.printPreOrderHelper(); //second do left
		if(right != null)
			right.printPreOrderHelper(); //third do right
	}
	private void printPostOrderHelper()
	{
		if(left != null) //first do left
			left.printPostOrderHelper();
		if(right != null) //second do right
			right.printPostOrderHelper();
		System.out.print(data+", "); //third do me!
	}
	private void printInOrderHelper()
	{
		if(left != null) //first do left
			left.printInOrderHelper();
		System.out.print(data+", "); //second to me
		if(right != null) //third do right
			right.printInOrderHelper();
	}
	
	public String toString()
	{
		return toStringPostOrder("");
	}
	
	private String toStringPreOrder(String currentSpace)
	{
		String s = "";
		
		s += currentSpace + data + "\n";

		if(left != null)
			s += left.toStringPreOrder(currentSpace+SPACE_DELIM);
//		else
//			s += currentSpace + SPACE_DELIM + "null\n";
		
		if(right != null)
			s += right.toStringPreOrder(currentSpace+SPACE_DELIM);
//		else 
//			s += currentSpace + SPACE_DELIM + "null\n";
			
		return s;
	}

	private String toStringPostOrder(String currentSpace)
	{
		String s = "";
		
		if(left != null)
			s += left.toStringPostOrder(currentSpace+SPACE_DELIM);
//		else
//			s += currentSpace + SPACE_DELIM + "null\n";
		
		if(right != null)
			s += right.toStringPostOrder(currentSpace+SPACE_DELIM);
//		else 
//			s += currentSpace + SPACE_DELIM + "null\n";

		s += currentSpace + data + "\n";

		return s;
	}

	private String toStringInOrder(String currentSpace)
	{
		String s = ""; //empty string
		
		if(left != null)
			s += left.toStringInOrder(currentSpace+SPACE_DELIM); //add the left's string
//		else //print out "null" as a branch
//			s += currentSpace + SPACE_DELIM + "null\n";

		s += currentSpace + data + "\n"; //add me
		
		if(right != null)
			s += right.toStringInOrder(currentSpace+SPACE_DELIM); //add the right's string
//		else 
//			s += currentSpace + SPACE_DELIM + "null\n";

		return s;
	}


	
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		BinaryTreeRec<Integer> tree = new BinaryTreeRec<Integer>(30,
			new BinaryTreeRec<Integer>(15, 
				new BinaryTreeRec<Integer>(4),
				new BinaryTreeRec<Integer>(20,
					new BinaryTreeRec<Integer>(18,
						null,
						new BinaryTreeRec<Integer>(19)
					),
					null
				)
			),
			new BinaryTreeRec<Integer>(35, 
				new BinaryTreeRec<Integer>(32),
				new BinaryTreeRec<Integer>(38)
			)
		);

		System.out.println(tree);
//		tree.printPreOrder();
//		tree.printPostOrder();
//		tree.printInOrder();
	}

}
