import java.util.*;
public class Book extends Item 
{

	private String author;
	private String genre;
	public static String [] childClassAttributes = {"ItemId", "Title","Author","Quantity", "Genre"};
	Map<String, Object> attributeMap = new HashMap<String, Object>();
	public Book(String title, String author, String genre, int quantity)
	{
		this.setQuantity(quantity);
		this.setTitle(title);
		this.author = author;
		this.genre = genre;
		this.setType("Book");
		attributeMap.put("ItemId", this.getId());
		attributeMap.put("Title", title);
		attributeMap.put("Author", author);
		attributeMap.put("Quantity", quantity);
		attributeMap.put("Genre", genre);

		
		
	}
	public void setAuthor(String a)
	{
		this.author = a;
	}
	public void setGenre(String a)
	{
		this.genre = a;
	}

	public String getAuthor()
	{		
		return author;
	}
	public String getGenre()
	{
		
		return genre;
	}
	public Object getAttributeByIndex(int index)
	{
		if(index == 0)
			return getId();
		else if(index == 1)
			return getTitle();
		else if(index == 2)
			return getAuthor();
		else if(index == 3)
			return getQuantity();
		else if(index == 4)
			return getGenre();
	
		
		return null;
	}
	
	public String toString()
	{
		return this.getType()+"; " +this.getId() + "; " +  this.getTitle()+"; " +this.getAuthor()+"; "+this.getQuantity()+"; "+this.getGenre()+"\n";
		
	}
	
}
