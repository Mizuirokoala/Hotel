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
import org.springframework.web.bind.annotation.PostMapping;

import pl.mizuirokoala.bean.LoginData;
import pl.mizuirokoala.bean.SessionManager;
import pl.mizuirokoala.entity.Hotel;
import pl.mizuirokoala.entity.ReservationDate;
import pl.mizuirokoala.repository.HotelRepository;
import pl.mizuirokoala.repository.MonthRepository;
import pl.mizuirokoala.repository.ReservationDateRepository;

@Controller
public class LoginHotelController {

	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private ReservationDateRepository reservationDateRepository;
	
	@Autowired
	private MonthRepository monthRepository;
	
	// hotel section
	@GetMapping("/loginHotel")
	public String loginHotel(Model m) {
		m.addAttribute("loginData", new LoginData());
		return "login/login_hotel";
	}
	
	@GetMapping("/registerHotel")
	public String registerHotel(Model m) {
		m.addAttribute("hotel", new Hotel());
		return "login/register_hotel";
	}
	
	@PostMapping("/registerHotel")
	public String registerHotelPost(@Valid @ModelAttribute Hotel hotel, BindingResult bindingResult, Model m) {
		if(bindingResult.hasErrors()) {
			return "login/register_hotel";
		}
		try {
			this.hotelRepository.save(hotel);			
		} catch (Exception e) {
			m.addAttribute("mailError", "The email is already used. Log in or use different email.");
			return "login/register_hotel";
		}
		//January
		for (int i = 1; i <= 31; i++) {
			ReservationDate rd = new ReservationDate(i, (i%7), this.monthRepository.findOne(1l), 2018, hotel, hotel.getCapacity());
			this.reservationDateRepository.save(rd);
		}
		//February
		for (int i = 1; i <= 28; i++) {
			ReservationDate rd = new ReservationDate(i, ((i+3)%7), this.monthRepository.findOne(2l), 2018, hotel, hotel.getCapacity());
			this.reservationDateRepository.save(rd);
		}
		//March
		for (int i = 1; i <= 31; i++) {
			ReservationDate rd = new ReservationDate(i, ((i+3)%7), this.monthRepository.findOne(3l), 2018, hotel, hotel.getCapacity());
			this.reservationDateRepository.save(rd);
		}
		//April
		for (int i = 1; i <= 30; i++) {
			ReservationDate rd = new ReservationDate(i, ((i+6)%7), this.monthRepository.findOne(4l), 2018, hotel, hotel.getCapacity());
			this.reservationDateRepository.save(rd);
		}
		//May
		for (int i = 1; i <= 31; i++) {
			ReservationDate rd = new ReservationDate(i, ((i+1)%7), this.monthRepository.findOne(5l), 2018, hotel, hotel.getCapacity());
			this.reservationDateRepository.save(rd);
		}
		//June
		for (int i = 1; i <= 30; i++) {
			ReservationDate rd = new ReservationDate(i, ((i+4)%7), this.monthRepository.findOne(6l), 2018, hotel, hotel.getCapacity());
			this.reservationDateRepository.save(rd);
		}
		//July
		for (int i = 1; i <= 31; i++) {
			ReservationDate rd = new ReservationDate(i, ((i+6)%7), this.monthRepository.findOne(7l), 2018, hotel, hotel.getCapacity());
			this.reservationDateRepository.save(rd);
		}
		//August
		for (int i = 1; i <= 31; i++) {
			ReservationDate rd = new ReservationDate(i, ((i+2)%7), this.monthRepository.findOne(8l), 2018, hotel, hotel.getCapacity());
			this.reservationDateRepository.save(rd);
		}
		//September
		for (int i = 1; i <= 30; i++) {
			ReservationDate rd = new ReservationDate(i, ((i+5)%7), this.monthRepository.findOne(9l), 2018, hotel, hotel.getCapacity());
			this.reservationDateRepository.save(rd);
		}
		//October
		for (int i = 1; i <= 31; i++) {
			ReservationDate rd = new ReservationDate(i, ((i+0)%7), this.monthRepository.findOne(10l), 2018, hotel, hotel.getCapacity());
			this.reservationDateRepository.save(rd);
		}
		//November
		for (int i = 1; i <= 30; i++) {
			ReservationDate rd = new ReservationDate(i, ((i+3)%7), this.monthRepository.findOne(11l), 2018, hotel, hotel.getCapacity());
			this.reservationDateRepository.save(rd);
		}
		//December
		for (int i = 1; i <= 31; i++) {
			ReservationDate rd = new ReservationDate(i, ((i+5)%7), this.monthRepository.findOne(12l), 2018, hotel, hotel.getCapacity());
			this.reservationDateRepository.save(rd);
		}
		HttpSession s = SessionManager.session();
		s.setAttribute("hotel", hotel);
		return "redirect:/";
	}
	
	@PostMapping("/loginHotel")
	public String loginHotelPost(@Valid @ModelAttribute LoginData loginData, Model m, BindingResult bindingResult) {
		Hotel h = this.hotelRepository.findOneByEmail(loginData.getEmail());
			if (h != null && h.isPasswordCorrect(loginData.getPassword())) {
				HttpSession s = SessionManager.session();
				s.setAttribute("hotel", h);
				return "redirect:/hotel/showReservations";
			}
			m.addAttribute("msg", "Enter valid data");
			return "login/login_hotel";
	}
	
	@ModelAttribute("availableHotels")
	public List<Hotel> getHotels() {
		return this.hotelRepository.findAll();
	}

}
