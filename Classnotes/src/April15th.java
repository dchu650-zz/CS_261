//MAPS
//How does a hashmap work?
	//Use a key to return a value
		//Key ties the values together
//Mapping - a function that moves from one value to another 
	//Basic mathematical example: y = f(x) f(x) -> y
	//Basic compsci example: parseInt(String) String -> Int
	//Basic compsci example: toString() object -> String
	//To make the map of the hashmap, one could use the set interface. (SET API JAVA UTIL)
//Set - A list that contains items however all of the things are unique. 
	//There is no get method. Everything is just there. No order that is associated. All we can say is, we have this many things.
	//Can be used for both the key and the values.
//HashCode
	//Change string into numbers in order to know how to store things.
	//Use to take strings, break them apart by each letter and get the unit code associated with each of them and then line them up.
	//The equation is located in the java docs. The Algorithm is s[0]*31^(n-1) + s[1]*31(n-2)+...
	//int hashcode = 'C'*(int)Math.pow(31,3-1) + 'a'*(int)Math.pow(31,3-1)+'t'*(int)Math.pow(31,3-3)
	//int hashcode = 'C'*31*31+'a'*31+'t';
	//System.out.println(hashcode);
	//System.out.println("Cat".hashCode());
//"Tom"		String Tom object is the key while the object is the value 
//"Dick"		
//"Harry"
//"Sam"
//"Pete"
	//System.out.println("Tom".hashCode()%5);  //Goes to spot four
	//System.out.println("Dick".hashCode()%5);	//Goes to spot zero
	//System.out.println("Harry".hashCode()%5);	//Goes to spot three
	//System.out.println("Sam".hashCode()%5);	//Goes to spot one
	//System.out.println("Pete".hashCode()%5);
	//Compare keys!
	//Now we double it! USE PRIME NUMBERS
	//System.out.println("Tom".hashCode()%11);  //Goes to spot four
	//System.out.println("Dick".hashCode()%11);	//Goes to spot zero
	//System.out.println("Harry".hashCode()%11);	//Goes to spot three
	//System.out.println("Sam".hashCode()%11);	//Goes to spot one
	//System.out.println("Pete".hashCode()%11);
	//Algorithm for comparison! c=1/2(1+1/(1-L)) L = number of objects divided by the mod number! (Mod number) is the number of slots in the list including 0
	//pg 380
	//read the book
	//Algorithm to decide how long it will take to fill up the spots. The larger the slower. More comparisons. = .5(1+1/(1-L)). L is the % of how full the table is.

//LINEAR PROBING
	//K = K + 1 (K is the slot that "has something in it")
//QUADRATIC PROBING
	//K = K + 1^2
	//K = K + 2^2
	//K = K + 3^2
	//...

//Shove everything that is the same into the same box. Chaining!
//Linked list size is the 50 / 100. There are 50 items, and there are 100 total boxes.
//Probing time = .5(1+1/(1-L))
//Chaining time = 1+(L/2)
//ArrayList : Vector :: HashMap : HashTable

//GRAPHS
	//G:{V,E} Graph:Collection of {Vertex, Edge}
	//V:{A,B,C,D,E}
	//A,B,C,D,E are nodes. Wrapper classes that we can put data inside
	//E:{(A,B), (B,C), (A,C), (B,E), (D,A)}
	//   A---B  B---C  A---C  B---E  D---A   (Draw these "nodes" onto paper and then link them by these lines.)
	//For example, they are roads. Linking one thing to another thing.
	//|V| = number of vertices (notation)
	//|E| = number of edges (notation)
	//Degree = how many edges are coming out of the vertex = d(V)
	//Σ[d(V)] = 2|E|
	//Uses of graphs: How to get from point to point! (Called looking for a path)
	//Weight: By giving an edge a value amount, the edges can be denoted by importance! 
	//Cannot have duplicates because w do not two of the same vertices. 
	//If the vertices goes in a circle, it is called a cycle. For Example: {(A,B), (B,C), (C,A)} Doing from one node to another and back to the original without doing through an already visited node.
	//Unconnected graphs are okay! Even though all nodes are not completely connected, it isn't a problem.
	//Not completed graphs or any graph without a cycle can be called a tree because they basically are trees. :)
	//Can make sub graphs within a graph! For Example: Can pull out a tree from a graph or can pull out cycles.
	//Complete graphs: all nodes are connected in graph. Denoted as K3, K4... K = Complete graph. # = number of nodes in graph.
	//Color-able: Can give each node a different color. However nodes can have the same color, they just cannot be linked. 
	//Adjacency Matrix: A 2dArray that represents the nodes two connected nodes in the 2dArray. Stored in a grid. Hashing an edge! 
	//Breadth first search || Depth first search.
	//Breadth first search: Check the first nodes that are connected to the node. Then check the nodes that are connected to that node...etc etc...
	//Depth first search: Is backtracking! 
//BUBBLE SORT
	//Swap adjacent numbers!  65738124...56738124...56378124...56371824...56371284 etc etc...
	//Swaps one object at a time. Can only swap in one direction. Therefore, it swaps slowly. Compare and then swap!
	//Counting the number of comparisons is good. Counting the number of swaps is not as convenient. The more comparisons the better than swaps. Swaps have to do comparisons and swap.
	//Speed of comparison: Best = O(n) Worst = O(n^2) || Speed of swap: = Best:O(1) Worst = O(n^2)
	//Bubble sort is easy but not to convenient. =(
//SELECTION SORT
	//Go through the list, put the smallest in the front. Then put the second smallest and then put that in front of the smallest...etc etc.
	//Find the smallest guy 65318724 (1) Comparison is order n
	//16538724 ... 12653874 ... 12365874 ... 12346587
	//Speed of comparison: Best = O(n^2) Worst = O(n^2) || Speed of swap: = Best:O(n) Worst = O(n)
//INSERTION SORT
	//Start with just one object. Add another object and then compare. Then grab another one and compare and another...etc. Insert something into a sorted list.
	//65318724 Look at six. Says good can't move it. Then I look at 5. Insert five in front of 5. Then look at 3, then move 6 into 3 spot and 5 into six spot and 3 in front. etc.
	//Speed of comparison: Best = O(n) Worst = O(n^2) || Speed of swap: = Best:O(n) Worst = O(n^2)
//SHELL SORT
	//Divide and conquer method. Feels like a recursive method. Break it up into pieces and then sort them. 
	//Let's say that we break up the items 1, 5 and 10. increment by 5. Then check them. Don't keep track of the amount of numbers but the numbers in between!
	//Then do the process with smaller gaps. The smaller the gaps, the fast the sorting is!
	//When the list almost completely sorted, use gap 1.
	//Much fewer comparisons! Good to divide the gaps by 2.2 or 2.5! 
	//2^k => O(n^2)
	//2^k + || - 1 => O(n^(4/3))
//MERGE SORT	
	//Break into smaller lists and then add them together. 65318724
	//Break into sorted sublists.
	//Split in half, 
	//mergeSort(half1)
	//mergeSort(half2)
	//mergeSort(half1,half2)
	//O(n lgn)
	//FASTEST FORM OF SORTING!! O(n lgn) is the fastest!
//GENERIC METHODS
	//Specify what type of "object this method will use" when we call it.





















