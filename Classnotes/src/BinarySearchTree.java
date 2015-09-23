
/**
 * A binary search tree (node-based)
 */
//extends binaryTree, and our "type" needs to be comparable
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E>
{

	public BinarySearchTree(E data, BinaryTree<E> leftBranch, BinaryTree<E> rightBranch)
	{
		super(data, leftBranch, rightBranch);		
		
		if( (leftBranch != null && !(leftBranch instanceof BinarySearchTree<?>)) || 
			(rightBranch != null && !(rightBranch instanceof BinarySearchTree<?>)) )
			throw new IllegalArgumentException("A subtrees of a BinarySearchTree must also be BinarySearchTree.");
	}

	public BinarySearchTree(E data){
		super(data);
	}
	
	public BinarySearchTree(){
		super();
	}
	
	
	//search the tree for the target
	//launcher method
	public E search(E target)
	{
		Node<E> result = search(root,target); //call the helper
		if(result == null)
			return null;
		return result.data;
	}

	
	//recursive helper; search node for the target
	private Node<E> search(Node<E> node, E target)
	{
		if(node == null) //stopping condition
			return null;

		int compare = node.data.compareTo(target); //compare them!
		if(compare == 0)
			return node; //found data, return the node
		else if(compare < 0) //if target comes after the node
			return search(node.right, target); //search the right branch
		else
			return search(node.left, target); //search the left branch
	}

	
	
	public boolean contains(E target)
	{
		return search(target)==null;
	}

	
	//launcher method
	public boolean add(E item)
	{
		if(root == null) //if we were empty, just add the node
		{
			root = new Node<E>(item);
			return true;
		}
		return add(root, item); //call the helper
	}

	
	//try to add node as child of the parent
	private boolean add(Node<E> parent, E item)
	{
		int compare = parent.data.compareTo(item); //compare to parent
		if(compare == 0)
			return false; //already in the list
		else if(compare < 0) //if the parent comes before us (we're after the parent)
		{
			if(parent.right == null) 
			{ 
				parent.right = new Node<E>(item); //add ourselves to the right spot
				return true;
			}
			return add(parent.right, item); //recurse!
		}
		else //parent comes after us
		{
			if(parent.left == null) //same thing: add ourselves if there is a spot
			{
				parent.left = new Node<E>(item);
				return true;
			}
			return add(parent.left, item);
		}
	}

	
	
	public E delete(E target)
	{
		return delete(root,target,null);
	}

	private E delete(Node<E> node, E target, Node<E> parent)
	{
		if(node == null) //didn't find the guy
			return null;
		
		int compare = node.data.compareTo(target);
		if(compare < 0) //this is recursively searching for the target
			return delete(node.right, target, node);
		else if(compare > 0)
			return delete(node.left, target, node);
		else //found the node
		{
			if(node.right != null && node.left != null) //two children
			{
				E toReturn = node.data; //who we're going to remove (so we can return)
				Node<E> replacement = findSmallestChild(node.right); //find replacement leftmost child of the right subtree
				node.data = delete(replacement.data); //delete the replacement and put him in the current node!
				return toReturn; //return who we first removed
			}
			else if(node.right != null) //has right child (but left must be null)
			{
				replaceInParent(node, parent, node.right); //replace me with my right
			}
			else if(node.left != null) //has left child (but right must be null)
			{
				replaceInParent(node, parent, node.left); //replace me with my left
			}
			else //is a leaf
			{
				replaceInParent(node, parent, null); //replace me with null
			}
		
			return node.data;
		}
	}
	
	//replaces node (who has given parent) with the given replacement
	private void replaceInParent(Node<E> node, Node<E> parent, Node<E> replacement)
	{
		if(parent == null) //if my parent is null, I am the root
		{
			if(replacement != null) //assign my data/trees/etc to the root
			{
				root.data = replacement.data;
				root.left = replacement.left;
				root.right = replacement.right;
			}
			else
				root = null; //otherwise make the root null
		}
		else if(parent.left == node) //if I am my parent's left child
			parent.left = replacement; //replace my parent's left (me) with the replacement
		else //if I am my parent's right child
			parent.right = replacement; //replace my parent's right (me) with the replacement
	}
	
	//leftmost child of the right subtree!
	private Node<E> findSmallestChild(Node<E> node)
	{
		if(node.left == null) //if I have no left
			return node; //I am leftmost
		return findSmallestChild(node.left); //keep going left!
	}
	
	
	public boolean remove(E target)
	{
		return delete(target) == null;
	}

	
	
	public static void main(String[] args)
	{
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(45);
		tree.add(30);
		tree.add(15);
		tree.add(50);
		tree.add(60);
		tree.add(20);
		tree.add(35);
		
		System.out.println(tree);
		
	}

	
}
