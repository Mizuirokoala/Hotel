package pl.mizuirokoala.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.mizuirokoala.bean.SessionManager;
import pl.mizuirokoala.entity.Hotel;
import pl.mizuirokoala.entity.Month;
import pl.mizuirokoala.entity.Pet;
import pl.mizuirokoala.entity.ReservationDate;
import pl.mizuirokoala.entity.User;
import pl.mizuirokoala.repository.HotelRepository;
import pl.mizuirokoala.repository.MonthRepository;
import pl.mizuirokoala.repository.PetRepository;
import pl.mizuirokoala.repository.ReservationDateRepository;
import pl.mizuirokoala.repository.UserRepository;

@Controller
@RequestMapping("reservationDate")
public class ReservationDateController {

	@Autowired
	private ReservationDateRepository reservationDateRepository;

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MonthRepository monthRepository;

	// DIRECT PET RESERVATION OPTION
	@GetMapping("/{petId}/newPetReservation")
	public String newReservation(Model m, @PathVariable long petId) {
		Pet pet = this.petRepository.findOne(petId);
		HttpSession s = SessionManager.session();
		s.setAttribute("chosenPet", pet);
		return "redirect:/";
	}

	// HOTEL OPTIONS
	@GetMapping("/{hotelId}/newHotelReservation")
	public String newHotelReservation(Model m, @PathVariable long hotelId) {
		HttpSession s = SessionManager.session();
		long tMonth = (long) s.getAttribute("tMonth");
		Month chosenMonth = this.monthRepository.findOne(tMonth);
		Hotel hotel = this.hotelRepository.findOne(hotelId);
		List<ReservationDate> hotelDates = this.reservationDateRepository
				.findAllByHotelIdAndMonthIdOrderById(hotel.getId(), chosenMonth.getId());
		s.setAttribute("chosenHotel", hotel);
		s.setAttribute("chosenMonth", chosenMonth);
		// s.setAttribute("hotel", hotel);
		s.setAttribute("hotelDates", hotelDates);
		// m.addAttribute("reservation", new ReservationDate());
		return "reservation/hotel_reservation_date";
	}

	@GetMapping("/hotel/clear")
	public String clearHotel(Model m) {
		HttpSession s = SessionManager.session();
		s.setAttribute("chosenHotel", null);
		s.setAttribute("chosenDate", null);
		s.setAttribute("chosenMonth", null);
		return "redirect:/";
	}

	@GetMapping("/{monthId}/month")
	public String setMonth(Model m, @PathVariable long monthId) {
		Month chosenMonth = this.monthRepository.findOne(monthId);
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("chosenHotel");
		s.setAttribute("chosenMonth", chosenMonth);
		List<ReservationDate> hotelDates = this.reservationDateRepository
				.findAllByHotelIdAndMonthIdOrderById(hotel.getId(), chosenMonth.getId());
		s.setAttribute("hotelDates", hotelDates);
		return "reservation/hotel_reservation_date";
	}

	// DATE OPTIONS
	@GetMapping("/{dateId}/date")
	public String newHotelReservationDate(Model m, @PathVariable long dateId) {
		HttpSession s = SessionManager.session();
		List<ReservationDate> chosenDate = (List<ReservationDate>) s.getAttribute("chosenDate");
		if (chosenDate == null) {
			chosenDate = new ArrayList<>();
		}
		ReservationDate rs = this.reservationDateRepository.findOne(dateId);
		// making sure there are no repetitions
		for (ReservationDate reservationDate : chosenDate) {
			if (reservationDate.getId() == rs.getId()) {
				chosenDate.remove(rs);
				s.setAttribute("chosenDate", chosenDate);
				return "reservation/hotel_reservation_date";
			}
		}
		chosenDate.add(rs);
		s.setAttribute("chosenDate", chosenDate);
		return "reservation/hotel_reservation_date";
	}

	@GetMapping("/clear")
	public String clearDates(Model m) {
		HttpSession s = SessionManager.session();
		s.setAttribute("chosenDate", null);
		return "reservation/hotel_reservation_date";
	}

	// PET OPTIONS
	@GetMapping("/{petId}/pet")
	public String choosePet(Model m, @PathVariable long petId) {
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("chosenHotel");
		Pet pet = this.petRepository.findOne(petId);
		s.setAttribute("chosenPet", pet);
		m.addAttribute("hotel", hotel);
		return "reservation/hotel_reservation_date";
	}

	@GetMapping("/pet/clear")
	public String clearPet(Model m) {
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("chosenHotel");
		Pet pet = null;
		s.setAttribute("chosenPet", pet);
		m.addAttribute("hotel", hotel);
		return "reservation/hotel_reservation_date";
	}

	@GetMapping("/showAll")
	public String showAll(Model m) {
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("chosenHotel");
		m.addAttribute("hotel", hotel);
		return "reservation/hotel_reservation_date";
	}

	// QUICK REGISTRATION WITH VALIDATION
	@PostMapping("/quickRegister")
	public String quickRegister(@RequestParam String petName, @RequestParam String petCategory,
			@RequestParam String userPassword, @RequestParam String email, RedirectAttributes ra, Model m) {
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("chosenHotel");
		m.addAttribute("hotel", hotel);

		List<User> allUsers = this.userRepository.findAll();
		if (petName.isEmpty()) {
			String petNameError = "Pet name cannot be empty";
			m.addAttribute("petNameError", petNameError);
			return "reservation/hotel_reservation_date";
		}
		if (petCategory.isEmpty()) {
			String categoryError = "Pet category cannot be empty";
			m.addAttribute("categoryError", categoryError);
			return "reservation/hotel_reservation_date";
		}
		if (email.isEmpty()) {
			String mailError = "Email cannot be empty";
			m.addAttribute("mailError", mailError);
			return "reservation/hotel_reservation_date";
		}
		if (!email.contains("@")) {
			String mailError = "Please use valid email";
			m.addAttribute("mailError", mailError);
			return "reservation/hotel_reservation_date";
		}

		for (User user : allUsers) {
			if (user.getEmail().equals(email)) {
				String mailError = "This email is already used. Log in or use different one.";
				m.addAttribute("mailError", mailError);
				return "reservation/hotel_reservation_date";
			}
		}
		Pet pet = new Pet();
		pet.setName(petName);
		pet.setCategory(petCategory);

		if (userPassword.isEmpty()) {
			pet.setTempUserEmail(email);
		} else {
			User user = new User();
			user.setEmail(email);
			user.setPassword(userPassword);
			user.setName("TemporaryName");
			pet.setUser(user);
			this.userRepository.save(user);
			s.setAttribute("user", user);
		}
		this.petRepository.save(pet);
		s.setAttribute("chosenPet", pet);
		s.setAttribute("chosenHotel", hotel);
		return "reservation/hotel_reservation_date";
	}

	@GetMapping("/removePet/{dateId}/{petId}")
	public String removePet(Model m, @PathVariable long dateId, @PathVariable long petId) {
		removeReservation(dateId, petId);
		return "redirect:/pet/show";

	}

	@GetMapping("/removePetHotel/{dateId}/{petId}")
	public String removePetHotel(Model m, @PathVariable long dateId, @PathVariable long petId) {
		removeReservation(dateId, petId);
		return "redirect:/hotel/showReservations";

	}

	// CONFIRM ALL
	@GetMapping("/confirm")
	public String confirmReservation(Model m) {
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("chosenHotel");
		Pet pet = (Pet) s.getAttribute("chosenPet");
		List<ReservationDate> dates = (List<ReservationDate>) s.getAttribute("chosenDate");

		// PET REPETITION CHECK - maybe extract to another method
		boolean repetition = false;
		List<ReservationDate> repetPetDates = new ArrayList<>();
		for (ReservationDate date : dates) {
			for (Pet p : date.getPet()) {
				if (p.getId() == pet.getId()) {
					repetition = true;
					repetPetDates.add(date);

				}
			}
		}

		if (repetition == true) {
			m.addAttribute("repetPetMessage", "Your pet is already registered for following dates:");
			m.addAttribute("repetPetDates", repetPetDates);
			return "reservation/hotel_reservation_date";
		} // END OF PET REPETITION CHECK

		for (ReservationDate date : dates) {
			date.addPet(pet);
			date.setHotel(hotel);
			date.setPlacesLeft(date.getPlacesLeft() - 1);
			this.reservationDateRepository.save(date);
		}
		m.addAttribute("hotel", hotel);
		m.addAttribute("pet", pet);
		m.addAttribute("dates", dates);
		m.addAttribute("datesNumber", dates.size());
		s.setAttribute("chosenHotel", null);
		s.setAttribute("chosenPet", null);
		s.setAttribute("chosenDate", null);
		return "reservation/confirmed";
	}

	@ModelAttribute("availableDates")
	public List<ReservationDate> getDates() {
		return this.reservationDateRepository.findAll();
	}

	@ModelAttribute("availablePets")
	public List<Pet> getPets() {
		return this.petRepository.findAll();
	}

	@ModelAttribute("availableHotels")
	public List<Hotel> getHotels() {
		return this.hotelRepository.findAll();
	}

	@ModelAttribute("months")
	public List<Month> getMonths() {
		return this.monthRepository.findAll();
	}

	public void removeReservation(long dateId, long petId) {
		ReservationDate date = this.reservationDateRepository.findOne(dateId);
		Pet pet = this.petRepository.findOne(petId);
		List<Pet> pets = date.getPet();

		List<ReservationDate> rs = pet.getReservationDate();
		ReservationDate toDeleteRS = null;
		Pet toDeletePet = null;

		for (ReservationDate reservationDate : rs) {
			if (reservationDate.getId() == dateId) {
				toDeleteRS = reservationDate;
			}
		}

		rs.remove(toDeleteRS);

		for (Pet pet2 : pets) {
			if (pet2.getId() == petId) {
				toDeletePet = pet2;
			}
		}

		pets.remove(toDeletePet);

		pet.setReservationDate(rs);
		this.petRepository.save(pet);

		date.setPet(pets);
		this.reservationDateRepository.save(date);

	}
}
