package cs544.sample;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Book {
	private int id;

	@NotBlank
	private String title;

	@NotBlank
	@Pattern(regexp = "\\d{3}-\\d{10}")
	private String ISBN;

	@NotBlank
	private String author;

	@NotNull
	@Min(value = 1)
	private Double price;

//	@AssertTrue
//	public boolean getPriceCheck(){
//		if(price>0) return true;
//		else return false;
//	}

	public Book() {
		super();
	}

	public Book(String title, String iSBN, String author, Double price) {
		super();
		this.title = title;
		ISBN = iSBN;
		this.author = author;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
