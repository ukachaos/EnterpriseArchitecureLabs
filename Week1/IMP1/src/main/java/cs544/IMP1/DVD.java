package cs544.IMP1;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("dvd")
public class DVD extends Product {
	private String genre;

	public DVD() {

	}

	public DVD(String name, String desc, String genre) {
		super(name, desc);
		this.genre = genre;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
}
