package cs544.CMP1;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

@Entity
public class Employee {
	@Id
	@GeneratedValue
	private int id;
	private String firstName;
	private String lastName;
	
	@OneToMany(mappedBy="employee", cascade=CascadeType.PERSIST)
	private Set<Laptop> laptops = new HashSet<>();

	public Employee() {
		
	}
	
	public Employee(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public boolean addLaptop(Laptop laptop) {
		laptop.setEmployee(this);
		return laptops.add(laptop);
	}
	
	public boolean removeLaptop(Laptop laptop) {
		laptop.setEmployee(null);
		return laptops.remove(laptop);
	}
	
	public Set<Laptop> getLaptops() {
		return laptops;
	}

	public void setLaptops(Set<Laptop> laptops) {
		this.laptops = laptops;
	}
}
