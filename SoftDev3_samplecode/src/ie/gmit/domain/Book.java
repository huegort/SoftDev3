package ie.gmit.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book extends Entity{
	String title;
	String author;
	Date datePublished;
	
	public Book(){
		
	}
	
	public Book(long id, String title, String author, Date datePublished) {
		super(id);
		this.title = title;
		this.author = author;
		this.datePublished = datePublished;
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
	public Date getDatePublished() {
		return datePublished;
	}
	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", datePublished=" + datePublished + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((datePublished == null) ? 0 : datePublished.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (datePublished == null) {
			if (other.datePublished != null)
				return false;
		} else {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
			if (!fmt.format(datePublished).equals(fmt.format(other.datePublished)))
				return false;
		}
			
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	

}
