package cs544.CMP1;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class Passenger {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	public Passenger() {
		
	}
	
	public Passenger(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null || obj.getClass() != this.getClass())
			return false;
		
		Passenger p = (Passenger) obj;
		
		if(id == p.id && name.equals(p.name))
			return true;
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
