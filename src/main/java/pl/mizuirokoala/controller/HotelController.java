package pl.mizuirokoala.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.mizuirokoala.bean.SessionManager;
import pl.mizuirokoala.entity.Hotel;
import pl.mizuirokoala.entity.Month;
import pl.mizuirokoala.entity.Pet;
import pl.mizuirokoala.entity.ReservationDate;
import pl.mizuirokoala.repository.HotelRepository;
import pl.mizuirokoala.repository.MonthRepository;
import pl.mizuirokoala.repository.PetRepository;
import pl.mizuirokoala.repository.ReservationDateRepository;

@Controller
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private ReservationDateRepository reservationDateRepository;

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private MonthRepository monthRepository;

	@GetMapping("/view/{hotelId}")
	public String home(@PathVariable long hotelId, Model m) {
		Hotel hotel = this.hotelRepository.findOne(hotelId);
		m.addAttribute("hotel", hotel);
		return "hotel/hotel_info";
	}

	@GetMapping("/showReservations")
	public String showReservations(Model m) {
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("hotel");
		long tMonth = (long) s.getAttribute("tMonth");
		Month chosenMonth = this.monthRepository.findOne(tMonth);
		List<ReservationDate> hotelDates = this.reservationDateRepository
				.findAllByHotelIdAndMonthIdOrderById(hotel.getId(), chosenMonth.getId());
		s.setAttribute("chosenHotel", hotel);
		s.setAttribute("chosenMonth", chosenMonth);
		s.setAttribute("hotelDates", hotelDates);

		return "hotel/reservation_view";
	}

	@GetMapping("/{monthId}/month")
	public String setMonth(Model m, @PathVariable long monthId) {
		Month chosenMonth = this.monthRepository.findOne(monthId);
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("hotel");
		s.setAttribute("chosenMonth", chosenMonth);
		List<ReservationDate> hotelDates = this.reservationDateRepository
				.findAllByHotelIdAndMonthIdOrderById(hotel.getId(), chosenMonth.getId());
		s.setAttribute("hotelDates", hotelDates);
		return "hotel/reservation_view";
	}

	@GetMapping("/showReservations/{dateId}")
	public String showReservations(Model m, @PathVariable long dateId) {
		ReservationDate hotelDate = this.reservationDateRepository.findOne(dateId);
		m.addAttribute("hotelDate", hotelDate);
		return "hotel/reservation_view";
	}

	@GetMapping("/showReservations/{dateId}/changeCapacityUp")
	public String changeCapacity(Model m, @PathVariable long dateId) {
		ReservationDate hotelDate = this.reservationDateRepository.findOne(dateId);
		hotelDate.setPlacesLeft(hotelDate.getPlacesLeft() + 1);
		this.reservationDateRepository.save(hotelDate);
		m.addAttribute("hotelDate", hotelDate);
		return "hotel/reservation_view";
	}

	@GetMapping("/showReservations/{dateId}/changeCapacityDown")
	public String changeCapacityDown(Model m, @PathVariable long dateId) {
		ReservationDate hotelDate = this.reservationDateRepository.findOne(dateId);
		hotelDate.setPlacesLeft(hotelDate.getPlacesLeft() - 1);
		this.reservationDateRepository.save(hotelDate);
		m.addAttribute("hotelDate", hotelDate);
		return "hotel/reservation_view";
	}

	@GetMapping("/showPet/{petId}")
	public String showPet(Model m, @PathVariable long petId) {
		Pet pet = this.petRepository.findOne(petId);
		m.addAttribute("pet", pet);
		return "hotel/show_pet";
	}

	// TODO - finalize edit function
	@GetMapping("/edit")
	public String changeUser(Model m) {
		HttpSession s = SessionManager.session();
		Hotel h = (Hotel) s.getAttribute("hotel");
		m.addAttribute("hotel", h);
		return "hotel/edit_hotel";
	}

	@PostMapping("/edit")
	public String changePost(@Valid @ModelAttribute Hotel hotel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "hotel/edit_hotel";
		}
		HttpSession s = SessionManager.session();
		Hotel h = (Hotel) s.getAttribute("hotel");
		hotel.setId(h.getId());
		hotel.setCapacity(h.getCapacity());
		hotel.setCodedPassword(h.getPassword());
		hotel.setReservationDate(h.getReservationDate());
		this.hotelRepository.save(hotel);
		return "redirect:/";
	}

	@ModelAttribute("availableHotels")
	public List<Hotel> getHotels() {
		return this.hotelRepository.findAll();
	}

	@ModelAttribute("months")
	public List<Month> getMonths() {
		return this.monthRepository.findAll();
	}
}
