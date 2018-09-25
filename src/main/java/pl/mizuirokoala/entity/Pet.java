package pl.mizuirokoala.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Pet {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	private String name;
	
	private int age;

	@NotBlank
	private String category;
	
	private String breed;
	
	private String diet;
	
	private String description;
	
	private String requirements;
	
	private String tempUserEmail;
	
	private boolean vaccinated;
	
	@ManyToOne
	private User user;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pet")
	private List<ReservationDate> reservationDate = new ArrayList<>();

	public Pet() {
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getDiet() {
		return diet;
	}

	public void setDiet(String diet) {
		this.diet = diet;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public boolean isVaccinated() {
		return vaccinated;
	}

	public void setVaccinated(boolean vaccinated) {
		this.vaccinated = vaccinated;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return id + "::" + name + " :: " + category;
	}

	public List<ReservationDate> getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(List<ReservationDate> reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getTempUserEmail() {
		return tempUserEmail;
	}

	public void setTempUserEmail(String tempUserEmail) {
		this.tempUserEmail = tempUserEmail;
	}
	
	
	
}
