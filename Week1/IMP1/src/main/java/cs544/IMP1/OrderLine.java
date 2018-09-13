package cs544.IMP1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class OrderLine {
	@Id
	@GeneratedValue
	private int id;
	private int quantity;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name="product_id")
	private List<Product> products = new ArrayList<>();
	
	public OrderLine() {
		
	}
	
	public OrderLine(int quantity) {
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public boolean addProduct(Product product) {
		return products.add(product);
	}
}
