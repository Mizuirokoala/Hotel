package pl.mizuirokoala.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Month {

	@Id
	private long id;
	
	private String name;
	
	private int dayDifference;
	
	@OneToMany(mappedBy = "month", fetch = FetchType.EAGER)
	private List<ReservationDate> reservationDate = new ArrayList<>();

	private int year;
	
	public Month(long id, String name, int dayDifference, int year) {
		super();
		this.id = id;
		this.name = name;
		this.dayDifference = dayDifference;
		this.year = year;
	}

	public Month(long id, String name, int dayDifference) {
		super();
		this.id = id;
		this.name = name;
		this.dayDifference = dayDifference;
	}

	public Month() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDayDifference() {
		return dayDifference;
	}

	public void setDayDifference(int dayDifference) {
		this.dayDifference = dayDifference;
	}

	public List<ReservationDate> getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(List<ReservationDate> reservationDate) {
		this.reservationDate = reservationDate;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}


}
