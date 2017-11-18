
public class Book extends Item
{

	private String author;
	private String genre;
	public static Object [] childClassAttributes = {"Title","Author","Quantity", "Genre"};
	public Book(String title, String author, String genre, int quantity)
	{
		this.setQuantity(quantity);
		this.setTitle(title);
		this.author = author;
		this.genre = genre;
		this.setType("Book");
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
			return getTitle();
		else if(index == 1)
			return getAuthor();
		else if(index == 2)
			return getQuantity();
		else if(index == 3)
			return getGenre();
	
		
		return null;
	}
	
}
