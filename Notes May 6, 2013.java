//QUICK SORT METHOD
/**
* Number set = 65318724
* The first number is the "privot"
* We basically rotate the numbers around the six.
* Remove the six and see, does the 5 go before the 6? No, put it in front. Keep going that until you finish the numbers. The 6 is in its spot.
* Next pick something on the left or the right. Basically, we have made two lists. The numbers on the left are smaller while 
* the numbers on the right are larger.
* Pick any number on the left or right to pivot. 
*/

/**
* These are the methods for the QUICKSORT algorithm. The algorithm works at the speed O(nlogn). 
*
*/

public static void quickSort(Comparable[] list)
{
	quickSort(list,0,list.length-1);
}

private static void quickSort(Comparable[] list, int first, int last)
{
	if(fist<last)
	{
		int pivot = partition(list, fist, last)
		quickSort(list, first, pivot-1);
		quickSort(list, pivot+1, last)
	}
}

private static int partition(Comparable[] list, int first, int last)
{
	Comparable pivot = list[first];
	//System.out.println();

	int up = first;
	int down = last;
	do
	{
		while(up<last && list[up].compareTo(pivot)>=0)
			up++

		while(pivot.compareTo(list[down])<0)
			down--;

		if(up<down)
		{
			Comparable tmp = list[up];
			list[up] = list[down];
			list[down] = tmp;
		}
	}
	while(up<down);

	Comparable tmp = list[first];
	list[first] = list[down];
	list[down] = tmp;
}