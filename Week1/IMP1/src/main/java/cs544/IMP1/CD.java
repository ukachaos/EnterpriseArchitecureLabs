package cs544.IMP1;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("cd")
public class CD extends Product {
	private String artist;

	public CD() {

	}

	public CD(String name, String desc, String artist) {
		super(name, desc);
		this.artist = artist;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

}
