package cs544.CMP1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;

@Entity
public class Flight {
	@Id
	@GeneratedValue
	private int id;
	private String flightNumber;
	
	@Column(name="dest_from")
	private String from;
	
	@Column(name="dest_to")
	private String to;
	
	@Temporal(TemporalType.DATE)
	private java.util.Date date;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	private List<Passenger> passengerList = new ArrayList<>();

	public Flight() {
		
	}
	
	public Flight(String flightNumber, String from, String to, java.util.Date date) {
		this.flightNumber = flightNumber;
		this.from = from;
		this.to = to;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}
	
	public List<Passenger> getPassengerList() {
		return passengerList;
	}

	public void setPassengerList(List<Passenger> passengerList) {
		this.passengerList = passengerList;
	}
	
	public boolean addPassenger(Passenger passenger){
		return passengerList.add(passenger);
	}
	
	public boolean removePassenger(Passenger passenger) {
		return passengerList.remove(passenger);
	}
}
