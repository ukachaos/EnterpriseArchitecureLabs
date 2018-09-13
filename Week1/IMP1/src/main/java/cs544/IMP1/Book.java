package cs544.IMP1;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("book")
public class Book extends Product{
	private String title;
	
	public Book() {
		
	}
	
	public Book(String name, String desc, String title) {
		super(name, desc);
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
