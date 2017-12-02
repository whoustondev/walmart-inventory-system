/**
 * Just a class to have some sorting stuff inside of it. Makes it easier.
 * There exists Merge Sort and selectionsort, as well as some helper methods. 
 * This is more of a toolbox.  
 * @author whouston
 *
 */
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;
public class SortClass 
{
	/**
	 * You can use this main method if you would like to use sorting by itself and see how it works. 
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main (String[] args) throws IOException
	{
		Book a = new Book("1984", "George Orwell", "SciFi", 12);
		
		Book b = new Book("Linear Algebra", "Addison Wesley", "SciFi", 12);
		
		Book c= new Book("Python", "O'reilly", "Reference", 12);
		
		LinkedList<Book> books = new LinkedList<Book>();
		books.add(a);
		
		books.add(b);
		
		books.add(c);

		books = mergeSort(books, true, "Genre");
		Iterator it = books.iterator();
		while(it.hasNext() == true)
		{
			System.out.println(((Book)it.next()).getGenre());

		}
		
	}
	/**
	 * Wrapper function around the other mergesort function which does stuff via an array.
	 * Not sure how to merge sort stuff when it is in a LinkedList..Think it may not be possible?
	 * @param a
	 * @param ascending
	 * @param attributeToSortBy
	 * @return
	 */
	public static LinkedList mergeSort(LinkedList a, boolean ascending, String attributeToSortBy)
	{
		Item[] array = convertToArray(a);
		
		array = mergeSort(array, attributeToSortBy, ascending);

		LinkedList listToReturn = convertToLinkedList(array);
		
		return listToReturn;
	}
	
	public static Item[] mergeSort(Item[] data, String attributeToSortBy, boolean ascending)
	{
		if (data.length <= 1)
			return data;
		int midPoint = (data.length/2);
		Item [] leftArray = new Item[midPoint];
		Item [] rightArray = new Item[data.length-midPoint];
		// Here we split up data for the left portion of the array. ex. for  {1, 3, 6, 5}, leftArray = {1,3}
		for(int i = 0; i < midPoint; i++)
		{
			leftArray[i] = data[i];
		}	
		for(int r = midPoint; r < data.length; r++)
		{
			rightArray[r-midPoint] = data[r];
		}
		leftArray = mergeSort(leftArray, attributeToSortBy, ascending);
		rightArray = mergeSort(rightArray, attributeToSortBy, ascending);
		return merge(leftArray, rightArray, attributeToSortBy, ascending);
	}


	public static Item[] merge(Item [] leftArray, Item[] rightArray, String attributeToSortBy, boolean ascending)
	{
		Item results[] = new Item[leftArray.length + rightArray.length];
		// So we have to loops, and we keep track of where we are at with these here indices.
		// Once indexLeft and indexRight both are at their maximums, we are clear for landing. We are done.
		
		int indexLeft = 0;
		int indexRight = 0;
		
		// So we loop through the results array, and fill it with the items in the left and right array.
		
		for (int i = 0; i<results.length; i++)
		{
		
			if (indexLeft == leftArray.length)
			{
				results[i] = rightArray[indexRight];
				indexRight++;
			}
			else if (indexRight == rightArray.length)
			{
				results[i] = leftArray[indexLeft];
				indexLeft++;
			}

			else 
			{	
				if (ItemComparator.customComparator(leftArray[indexLeft], rightArray[indexRight], attributeToSortBy) <= -1)
				{
					if(ascending == true)
					{
						results[i] = leftArray[indexLeft];
						indexLeft++;
					}
					else
					{
						results[i] = rightArray[indexRight];
						indexRight++;
						
					}
				}
				else if (ItemComparator.customComparator(leftArray[indexLeft], rightArray[indexRight], attributeToSortBy) >= 1)				
				{
					if(ascending == true)
					{
						results[i] = rightArray[indexRight];
						indexRight++;
					}
					else
					{
						results[i] = leftArray[indexLeft];
						indexLeft++;
					}
				}
				else if (ItemComparator.customComparator(leftArray[indexLeft], rightArray[indexRight], attributeToSortBy) == 0)				
				{
					if(ascending == true)
					{
						results[i] = rightArray[indexRight];
						indexRight++;
					}
					else
					{
						results[i] = leftArray[indexLeft];
						indexLeft++;
					}
				}
				
				
			}
		}
		return results;
	}

	/**
	 * THIS FUNCTION IS NOW DEPRECACTED (It is not being used)
	 * We are keeping this around just in case stuff breaks and we need to revert back to it. 
	 * 
	 * This function takes a LinkedList -> preferably of books, clothes, etc. It will return the linkedlist in order of whatever attribute you provide 
	 *
	 * @param LinkedList a				<-- a linkedlist of items. very fancy. 
	 * @param boolean ascending     		<-- set to true if we want ascending to be true
	 * @param String attributeToSortBy	<-- we pass this a linkedlist of items and so attribute toSortBy picks the attribute to sort this whole thing by. 
	 *
	 *	This method also uses two methods, convertToLinkedList and convertToArray (in which we are reinventing the wheel a little bit)
	 *	Perhaps in the future we can widdle this down to JUST sorting the linkedlist - but I do find that hard and maybe even impossible...
	 *	Sorting an array allows for much more flexibility in accessing elements directly (which is inherent in sorting arrays)
	 * @return
	 *
	 */
	public static LinkedList selectionSort(LinkedList a, boolean ascending, String attributeToSortBy)
	{
		Item[] array = convertToArray(a);

		for(int i=0; i<a.size(); i++)
		{
			int k = i;
			if(ascending == true)
			{
				
				while( k > 0 && ItemComparator.customComparator(array[k], array[k-1], attributeToSortBy) <= 0)			
				{
					System.out.println(k);
					Item temp = array[k];
					array[k] = array[k-1];
					array[k-1] = temp;
					k--;
				}
			}
			else // if we want descending
			{
				//array[k-1].getTitle().compareTo(array[k].getTitle())
				while( k > 0 && ItemComparator.customComparator(array[k-1], array[k], attributeToSortBy) <= 0)			
				{
					//System.out.println(k);
					Item temp = array[k];
					array[k] = array[k-1];
					array[k-1] = temp;
					k--;
				}	
			}
		}
		
		
		LinkedList listToReturn = convertToLinkedList(array);
		return listToReturn;
	}
	
	/**
	 * This is a helper function to convert a LinkedList to an array.
	 * It is used in the sort function. 
	 * @param a
	 * @return
	 */
	public static Item[] convertToArray(LinkedList a)
	{
		Item[] array = new Item[a.size()];
		//Iterator it = a.iterator();
		//it.next();
		for(int i = 0; i< array.length; i++)
		{
			array[i] = (Item)a.get(i);	
		}
		
		return array;
	}
	/**
	 * This is a helper function. We use it in the sorting method.
	 * @param array
	 * @return
	 */
	public static LinkedList convertToLinkedList(Item[] array)
	{
		
		LinkedList listToReturn = new LinkedList();

		for(int i = 0; i < array.length; i++)
		{
			//System.out.println(((Book)array[i]).getTitle());	
			listToReturn.add(array[i]);
		}
		return listToReturn;
	}

}
