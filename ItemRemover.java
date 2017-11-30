
import java.io.*;
import java.util.*;
public class ItemRemover {
	
	public static LinkedList removeFromLinkedList(LinkedList a, int idToRemove)
	{
		
		Iterator it = a.iterator();
		while(it.hasNext())
		{	
			int id = ((Item)it.next()).getId();
			if(id == idToRemove)
			{
				it.remove();
			}
		}
		return a;
		
	}
	public static void removeItemFromDb(int itemId, String filepath)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));
			String line = "";
			String newFile = "";
			while((line = br.readLine()) != null)
			{	
				if(line.length() != 0)
				{
					String itemIdAsString = ""+itemId;
					String[] tokens = line.split(";");
					if(tokens[1].trim().contains(itemIdAsString))
					{
						System.out.println("item contains taht ID");
						
						
						
						
					}
					else
					{
						newFile += line+"\n";
						
					}
				}
				
			}
			br.close();
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filepath)));
			bw.write(newFile);
			bw.close();
			
		}
		catch(FileNotFoundException a)
		{
			a.printStackTrace();		
		}
		catch(Exception b)
		{
			b.printStackTrace();
		}
		
		
		
		
		
		
	}
}
