import java.util.ArrayList;
import java.util.Stack;
/**
 * This is the Graph class that does most of the work. It has a Stack of Document objects and an ArrayList of Edge objects.
 * The Stack of documents is the collection of files that is being examined, while the ArrayList of edges is the list of connections between documents (the weight of the edge is the number of similar n-grams).
 * This class also handles the work of comparing the documents. For the sake of memory efficiency, only two documents will be stored in memory at a time.
 * @author Kramer Canfield and Darren Chu
 * @version 4 May 2013
 */
public class Graph
{
	//fields
	private Stack<Document> theStack;//the stack of documents that the program is working on
	private ArrayList<Edge> edgeList;//this is a list of the edges
	private int sharedNGrams;//the number of shared n-grams necessary to make an edge.

	/**
	 * This is the constructor for the Graph. It initializes the 2D array and the Stack of documents.
	 */
	public Graph()
	{

		theStack = new Stack();
		edgeList = new ArrayList<Edge>();

	}




	/**
	 * 
	 * @param document1 The first document to check.
	 * @param document2 The second document to check.
	 * @return The number of n-grams that are shared between the documents, also known as the weight of the edge
	 */
	public int compareDocuments(Document document1, Document document2)
	{
		//System.out.println("d1: "+d1.getFileName()+"d2: "+d2.getFileName());

		int weight = 0;//this is the counter that we will increase when we find a similarity
		for(int i=0; i< document1.getNGramList().size(); i++)
		{
			int target = document1.getNGramList().get(i);//get the ith ngram from document1

			if(document2.getNGramList().contains(target))//if(binarySearchNGram(target, document2))//if we found a matching n-gram in document2//NOTE:cannot get binary search method working so, for now, use built-in java contains() method    
			{
				weight++;//then increase the weight
			}
		}
		return weight;//return the weight of the edge between the two documents
	}




	/**
	 * This method compares all of the documents and makes the list of edges with weights and file names.
	 */
	public void compareAllDocs()
	{
		while(!theStack.isEmpty())//while there are still documents left, keep going
		{
			//System.out.println("size of theStack: "+theStack.size());
			Document otherDoc;
			Document doc1 = theStack.pop();//remove the first guy (the head) and store it, this changes each time because we're popping off of theStack each time

			for(int i=0; i<theStack.size(); i++)//go through each document in the stack
			{
				otherDoc = theStack.get(i);//the document we're currently comparing doc1 with, the next item in theStack
				
				int edgeWeight = compareDocuments(doc1,otherDoc);//compare the documents

				//make a new edge and add the edge to the list of edges if the edge weight is greater than the user-specified value
				if(edgeWeight > sharedNGrams)
				{
					Edge theEdge = new Edge(edgeWeight, doc1.getFileName(), otherDoc.getFileName());//make a new Edge between the documents
					edgeList.add(theEdge);//add it to the list
				}
			}
		}

	}

	/**
	 * This is a binary search method we wrote so we could search the sorted ArrayList very quickly.
	 * @param target The number we're looking for.
	 * @param document2 The document we're searching through.
	 * @return True if the target was found, false if the target was not found.
	 */
	public boolean binarySearchNGram(int target, Document document2)
	{

		//search document2 for that ngram using a binary search algorithm
		int start=0;//start at the beginning
		int end = document2.getNGramList().size();
		int middle = (start+end)/2;//the middle index is halfway between the start and end

		
		while(end-start > 1)//while the start and end are not the same (while we're approaching where the target should be)
		{
			//System.out.println("in while loop in binary search");//FOR DEBUGGING INFINITE LOOP
			if(target < middle)//if the target is less than the middle
			{
				end = middle;//then the new end is the old middle because we don't need to search after the middle
				middle = (start+end)/2;//the new middle is between the old middle and the start 
			}
			else
			{
				start = middle;//because we don't need to look before the middle
				middle = (start+end)/2;//the new middle is between the old middle and the end 
			}
		}
		return document2.getNGramList().get(start)==target;//return true if we closed in on the target's location and the target was there
	}
	

	public void sortEdgeList()
	{
		for(int i=1; i<edgeList.size(); i++)
		{
			//adapted from Heap.add()
			int child = i;
			int parent = (child-1)/2;
			while(parent >=0 && edgeList.get(parent).getWeight() < edgeList.get(child).getWeight())
			{
				Edge tmp = edgeList.get(parent);
				edgeList.set(parent, edgeList.get(child));
				edgeList.set(child, tmp);

				child = parent;
				parent = (child-1)/2;
			}
		}
		
		for(int last= edgeList.size()-1; last > 0; last--)
		{
			Edge tmp = edgeList.get(0);
			edgeList.set(0, edgeList.get(last));
			edgeList.set(last, tmp);

			//adapted from Heap.pop()
			int current = 0;
			boolean adjusting = true; //sentinel
			while(adjusting)
			{
				int leftChild = current*2+1;
				int rightChild = leftChild+1; //get children

				if(leftChild < last) //start with left
				{
					int maxChild = leftChild;
					if(rightChild < last && edgeList.get(rightChild).getWeight() > edgeList.get(leftChild).getWeight())
						maxChild = rightChild; //see if should be right
	 				
					if(edgeList.get(current).getWeight()<edgeList.get(maxChild).getWeight())
					{
						//swap
						tmp = edgeList.get(maxChild);
						edgeList.set(maxChild, edgeList.get(current));
						edgeList.set(current, tmp);

						current = maxChild; //current min, who I just swapped
					}
					else
						adjusting = false;
				}
				else
					adjusting = false;
			}
		}
	}

	/**
	 * This method out the weight of the edges and the associated fileNames. This method only prints the specified number of edges, but thanks to method overloading, there is also a method that prints all of the results (all edges).
	 * @param numberToPrint The number of edges to print.
	 */
	public void printWeightList(int numberToPrint)
	{
		if(numberToPrint==0)//if zero is specified, then the user actually wants to see all results (for more details, see PlagiarismUI) 
		{
			printWeightList();//then print all results
		}

		else if(numberToPrint > edgeList.size())//if the user specified a number that is too big
		{
			System.out.println("********There were more results requested than are available. Here are all of the results.********");//tell the user
			printWeightList();//then print as many results as possible (all of them)
		}
		
		else if(numberToPrint < edgeList.size())//if the user-specified value is okay, then print out the first numberToPrint results
		{
			for(int i=0; i<numberToPrint; i++)
			{	
				if(edgeList.get(i).getWeight() != 0)
				{
					System.out.println(edgeList.get(i).getWeight() + ": " + edgeList.get(i).getNames());
				}
			}
		}
	}

	/**
	 * This method out the weights of all of the edges and the associated fileNames. 
	 */
	public void printWeightList()
	{
		for(int i=0; i<edgeList.size(); i++)//for the edges in the edgeList
		{
			System.out.println(edgeList.get(i).getWeight() + ": " + edgeList.get(i).getNames());//stylistically print the edge weights and the file names as the results of the program
		}
	}

	/**
	 * The get method for the stack of documents.
	 * @return theStack The stack of documents.
	 */
	public Stack<Document> getTheStack()
	{
		return theStack;
	}
	
	/**
	 * This method sets the number of similar n-grams between documents necessary for an edge to be formed and added to the list of edges.
	 * @param x The new value of sharedNGrams.
	 */
	public void setSharedNGrams(int x)
	{
		sharedNGrams = x;
	}

}