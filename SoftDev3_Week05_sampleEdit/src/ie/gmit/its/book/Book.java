package ie.gmit.its.book;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class Book {
	long id;
	String title;
	String author;
	int price;
	Date datePublished;
	String genre;
	
	public Book(){
	}
	public Book(long id, String title, String author, int price, Date datePublished, String genre) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.datePublished = datePublished;
		this.genre = genre;
	}
	public Book(HttpServletRequest request) throws IOException{
		String idString = request.getParameter("id");
		try{
			this.id= Long.parseLong(idString);
		}catch(NumberFormatException nfe){
			this.id=-1;
		}
		this.title = request.getParameter("title");
		this.author = request.getParameter("author");
		String datePublishedString = request.getParameter("datePublished");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.datePublished = formatter.parse(datePublishedString);
		} catch (ParseException e) {
			throw new IOException(e);
		}
		this.genre = request.getParameter("genre");
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}


	public Date getDatePublished() {
		return datePublished;
	}
	public String getDatePublishedAsSQLString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		return formatter.format(this.datePublished);
	}
	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;
	}
	
	public String getGenre() {
		return genre;
	}




	public void setGenre(String genre) {
		this.genre = genre;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + price;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (id != other.id)
			return false;
		if (price != other.price)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", price=" + price + "]";
	}
	
}
