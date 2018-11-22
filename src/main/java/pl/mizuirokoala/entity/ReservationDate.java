package pl.mizuirokoala.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class ReservationDate {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private int day;
	
	private int weekDay;

	@ManyToOne
	private Month month;

	private int year;
	
	@ManyToOne
	private Hotel hotel;
	
	private int placesLeft;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(joinColumns = { @JoinColumn(name = "reservation_date_id") }, inverseJoinColumns = {
			@JoinColumn(name = "pet_id") })
	private List<Pet> pet = new ArrayList<>();

	public ReservationDate() {
		super();
	}

	public ReservationDate(int day, int weekDay, Month month, int year, Hotel hotel, int placesLeft) {
		super();
		this.day = day;
		this.weekDay = weekDay;
		this.month = month;
		this.year = year;
		this.hotel = hotel;
		this.placesLeft = placesLeft;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(int weekDay) {
		this.weekDay = weekDay;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public int getPlacesLeft() {
		return placesLeft;
	}

	public void setPlacesLeft(int placesLeft) {
		this.placesLeft = placesLeft;
	}

	public List<Pet> getPet() {
		return pet;
	}

	public void setPet(List<Pet> pet) {
		this.pet = pet;
	}
	
	public void addPet(Pet pet) {
		this.getPet().add(pet);
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public Month getMonth() {
		return month;
	}
	
	public int getAmount() {
		return this.pet.size();
	}


	
	
}
