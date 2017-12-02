import java.util.Comparator;
/**
 * This comparator class is very special. The actual compare function isn't very useful, since all it does is compare user IDs.
 * And let's face it. An Item has MANY attributes one might want to compare.
 * And so thus, we came up with our very own special customComparator method which takes the name of the attribute that you want to sort by. 
 * Below, we have cutomComparator which is good for sorting a list of items. 
 * It will compare two items and take in an attribute. 
 * Whatever attribute you give it, it will compare via that item's attribute. 
 * @author whouston
 *
 */
public class ItemComparator implements Comparator<Item>
{
	
	public int compare(Item a, Item b)
	{
		if(a.getId() < b.getId())
			return -1;
		else
			return 1;
	}



/**
 * This function takes in three arguments and it compares the two items for purposes of the sorting.
 * This is a little bit tricky since it has been refactored down. This function is used in the sorting function to compare stuff for ordering. 
 * But, just think of this function as a wrapper around the compareTo function. 
 * This function handles 2 items, and compares the attributes of those 2 items (depending on what kind - Book, Clothing, Toy, etc. )
 * First - checks to see what kind of objects we are dealing with by using instanceof on the two items. (if both aren't of the same class, we exit the function)
 * Second - we check to see what kind of object the attribute is. A String or an Integer are the most common
 * Third - if it's a string, we use compareTo of the attribute we grabbed (via the user choice) for the specific objects to compare against
 * 			if it's an int, it just returns a negative value (-1) if the first val is less than the second val so that the sort method knows to keep looping through objects. 
 * 
 * This function will not make any sense to you unless you also look at the sort function
 * 
 * @return
 */
public static int customComparator(Item a, Item b, String attributeToSortBy)
{	
	int comparatorValue = -1;
	if(a instanceof Clothing && b instanceof Clothing) // handles clothing
	{
		if(((Clothing)a).attributeMap.get(attributeToSortBy) instanceof String)
		{
			comparatorValue = ((String)((Clothing)a).attributeMap.get(attributeToSortBy)).toUpperCase().compareTo(((String)((Clothing)b).attributeMap.get(attributeToSortBy)).toUpperCase());
		}
		else if(((Clothing)a).attributeMap.get(attributeToSortBy) instanceof Integer)
		{	
			//System.out.println("is a less than b? : " + ((Clothing)a).attributeMap.get(Integer.parseInt(attributeToSortBy)) + "  " + ((Clothing)b).attributeMap.get(Integer.parseInt(attributeToSortBy)));
			if((Integer)((Clothing)a).attributeMap.get(attributeToSortBy) <= (Integer)((Clothing)b).attributeMap.get(attributeToSortBy))
			{
				System.out.println((Integer)((Clothing)a).attributeMap.get(attributeToSortBy) + "is less than " + (Integer)((Clothing)b).attributeMap.get(attributeToSortBy));
				comparatorValue = -1;
			}
			else
			{
				comparatorValue = 1;
			}
		}
	
	}
	else if (a instanceof Toy && b instanceof Toy) // Handles toys
	{
		if(((Toy)a).attributeMap.get(attributeToSortBy) instanceof String)
		{
			comparatorValue = ((String)((Toy)a).attributeMap.get(attributeToSortBy)).toUpperCase().compareTo(((String)((Toy)b).attributeMap.get(attributeToSortBy)).toUpperCase());
		}
		else if(((Toy)a).attributeMap.get(attributeToSortBy) instanceof Integer)
		{	
			//System.out.println("is a less than b? : " + ((Toy)a).attributeMap.get(Integer.parseInt(attributeToSortBy)) + "  " + ((Toy)b).attributeMap.get(Integer.parseInt(attributeToSortBy)));
			if((Integer)((Toy)a).attributeMap.get(attributeToSortBy) <= (Integer)((Toy)b).attributeMap.get(attributeToSortBy))
			{
				System.out.println((Integer)((Toy)a).attributeMap.get(attributeToSortBy) + "is less than " + (Integer)((Toy)b).attributeMap.get(attributeToSortBy));
				comparatorValue = -1;
			}
			else
			{
				comparatorValue = 1;
			}
		}		
	}
	else if (a instanceof Book && b instanceof Book) // Handles books
	{	
		if(((Book)a).attributeMap.get(attributeToSortBy) instanceof String)
		{
			comparatorValue = ((String)((Book)a).attributeMap.get(attributeToSortBy)).toUpperCase().compareTo(((String)((Book)b).attributeMap.get(attributeToSortBy)).toUpperCase());
		}
		else if(((Book)a).attributeMap.get(attributeToSortBy) instanceof Integer)
		{	
			//System.out.println("is a less than b? : " + ((Book)a).attributeMap.get(Integer.parseInt(attributeToSortBy)) + "  " + ((Book)b).attributeMap.get(Integer.parseInt(attributeToSortBy)));
			if((Integer)((Book)a).attributeMap.get(attributeToSortBy) <= (Integer)((Book)b).attributeMap.get(attributeToSortBy))
			{
				System.out.println((Integer)((Book)a).attributeMap.get(attributeToSortBy) + "is less than " + (Integer)((Book)b).attributeMap.get(attributeToSortBy));
				comparatorValue = -1;
			}
			else
			{
				comparatorValue = 1;
			}
		}
	}
	return comparatorValue;	
}


}
