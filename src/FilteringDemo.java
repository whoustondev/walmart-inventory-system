import java.util.*;

/**
 * 
 * @author whouston
 *
 */

public class FilteringDemo 
{
	public static void main(String[] args)
    {
        // Create a list of strings
        ArrayList<String> al = new ArrayList<String>();
        al.add("Geeks For Geeks");
        al.add("Friends");
        al.add("Dear");
        al.add("Is");
        al.add("Superb");
        al.add("Sup");
        al.add("Suv");
        
        /* Collections.sort method is sorting the
        elements of ArrayList in ascending order. */
        //Collections.sort(al, Collections.reverseOrder());
        
        // Let us print the sorted list
        //System.out.println("List after the use of" +
            //               " Collection.sort() :\n" + al);
        System.out.println(filter("Su", al));
        
    }
	
	public static ArrayList<String> filter(String criteria, ArrayList<String> a)
	{
		ArrayList<String> newList = new ArrayList<String>();
		
		for(String string: a)
		{
			System.out.println("inside for");
			if(string.startsWith(criteria))
			{	
				newList.add(string);
			}
			
			
		}
		return newList;
		
	}
}
