package cs544.hop1;

import org.hibernate.annotations.FetchMode;

import java.util.List;

import javax.persistence.*;

@Entity
public class Owner {
	@Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany (cascade={CascadeType.PERSIST}, fetch = FetchType.EAGER)
	//@org.hibernate.annotations.BatchSize(size=5)
	//@org.hibernate.annotations.Fetch(FetchMode.SUBSELECT)
    @JoinColumn (name="clientid")
    private List<Pet> pets;
    
	public Owner() {
	}
	public Owner(String name) {
		super();
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
	public List<Pet> getPets() {
		return pets;
	}
	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}
    
	
    
}
