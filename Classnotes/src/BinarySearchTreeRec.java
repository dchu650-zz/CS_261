/**
 * A BST implemented on a recursive tree. Delete method is unimplemented.
 */
public class BinarySearchTreeRec<E extends Comparable<E>> extends BinaryTreeRec<E>
{
	public BinarySearchTreeRec(E data, BinaryTreeRec<E> leftBranch, BinaryTreeRec<E> rightBranch)
	{
		super(data, leftBranch, rightBranch);
		
		if( (leftBranch != null && !(leftBranch instanceof BinarySearchTreeRec<?>)) || 
			(rightBranch != null && !(rightBranch instanceof BinarySearchTreeRec<?>)) )
			throw new IllegalArgumentException("Subtrees of a BinarySearchTree must also be BinarySearchTrees.");
	}

	public BinarySearchTreeRec(E data){
		super(data);
	}

	public BinarySearchTreeRec(){
		super();
	}

	
	
	public E search(E target)
	{
		int compare = data.compareTo(target);
		
		if(compare == 0)
			return data;
		else if(compare < 0 && right != null)
			return ((BinarySearchTreeRec<E>)right).search(target);
		else if(compare > 0 && left != null)
			return ((BinarySearchTreeRec<E>)left).search(target);
		
		return null;
	}

	public boolean contains(E target)
	{
		return search(target) == null;
	}

	
	
	public boolean add(E item)
	{
		if(data == null)
		{
			data = item;
			return true;
		}
		
		int compare = data.compareTo(item);
		if(compare < 0)
		{
			if(right == null){
				right = new BinarySearchTreeRec<E>(item);
				return true;
			}
			else
				return ((BinarySearchTreeRec<E>)right).add(item);
		}
		else if(compare > 0)
		{
			if(left == null){
				left = new BinarySearchTreeRec<E>(item);
				return true;
			}
			else
				return ((BinarySearchTreeRec<E>)left).add(item);
		}
		else
			return false;
	}

	
	
	public E delete(E target)
	{
		/**
		 * ???????????
		 * ???????????
		 * ???????????
		 */
		
		return null;
	}
		
	public boolean remove(E target)
	{
		return delete(target) == null;
	}


	
	public static void main(String[] args)
	{
		BinarySearchTreeRec<Integer> tree = new BinarySearchTreeRec<Integer>(45);
		tree.add(30);
		tree.add(15);
		tree.add(50);
		tree.add(60);
		tree.add(20);
		tree.add(25);
		tree.add(90);

		System.out.println(tree);
		
	}

}
