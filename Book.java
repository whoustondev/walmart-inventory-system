
public class Book extends Item
{
	private String title;
	private String author;
	private String genre;
	
	public Book(String title, String author, String genre, int quantity)
	{
		this.setQuantity(quantity);
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.setType("Book");
	}
	public void setAuthor(String a)
	{
		this.author = a;
	}
	public void setTitle(String a)
	{
		this.title = a;
	}
	public void setGenre(String a)
	{
		this.genre = a;
	}
	public String getTitle()
	{
		
		return title;
	}
	public String getAuthor()
	{
		
		return author;
	}
	public String getGenre()
	{
		
		return genre;
	}

	
}
