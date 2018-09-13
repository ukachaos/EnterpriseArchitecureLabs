package cs544.IMP1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Order_table")
public class Order {
	@Id
	@GeneratedValue
	private int orderid;
	@Temporal(value=TemporalType.DATE)
	private java.util.Date date;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name="orderline_id")
	private List<OrderLine> orderLines = new ArrayList<>();
	
	public Order() {
		
	}
	
	public Order(java.util.Date date) {
		this.date = date;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	
	public boolean addOrderLines(OrderLine orderLine) {
		return orderLines.add(orderLine);
	}
}
