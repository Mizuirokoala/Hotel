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
import pl.mizuirokoala.entity.Pet;
import pl.mizuirokoala.entity.ReservationDate;
import pl.mizuirokoala.entity.User;
import pl.mizuirokoala.repository.HotelRepository;
import pl.mizuirokoala.repository.PetRepository;
import pl.mizuirokoala.repository.ReservationDateRepository;

@Controller
@RequestMapping("pet")
public class PetController {

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private ReservationDateRepository reservationDateRepository;
	
	@Autowired
	private HotelRepository hotelRepository;

	@GetMapping("/register")
	public String registerPer(Model m) {
		m.addAttribute("pet", new Pet());
		return "pet/register_pet";
	}

	@PostMapping("/register")
	public String registerPost(@Valid @ModelAttribute Pet pet, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "pet/register_pet";
		}
		HttpSession s = SessionManager.session();
		User user = (User) s.getAttribute("user");
		pet.setUser(user);
		this.petRepository.save(pet);
		return "redirect:/";
	}

	@GetMapping("/show")
	public String showPets(Model m) {
		HttpSession s = SessionManager.session();
		User user = (User) s.getAttribute("user");
		Pet pet = (Pet) s.getAttribute("pet");
		if (pet == null) {
			List<Pet> userPets = user.getPet();
			if (!userPets.isEmpty()) {
				for (Pet p : userPets) {
					s.setAttribute("pet", p);
					List<ReservationDate> petDates = this.reservationDateRepository.findAllByPetId(p.getId());
					s.setAttribute("petDates", petDates);
					return "pet/show_pet";
				}
			}
		} else {
			List<ReservationDate> petDates = this.reservationDateRepository.findAllByPetId(pet.getId());
			s.setAttribute("petDates", petDates);
			
		}
		// m.addAttribute("pet1", new Pet());
		return "pet/show_pet";
	}

	@GetMapping("/show/{petId}")
	public String showPets(Model m, @PathVariable long petId) {
		Pet pet = this.petRepository.findOne(petId);
		HttpSession s = SessionManager.session();
		List<ReservationDate> petDates = this.reservationDateRepository.findAllByPetId(petId);
		s.setAttribute("pet", pet);
		s.setAttribute("petDates", petDates);
		return "pet/show_pet";
	}
	
	@GetMapping("/edit")
	public String changeUser(Model m) {
		HttpSession s = SessionManager.session();
		Pet p = (Pet) s.getAttribute("pet");
		m.addAttribute("pet", p);
		return "pet/edit_pet";
	}
	
	@PostMapping("/edit")
	public String changePost(@Valid @ModelAttribute Pet pet, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "pet/edit_pet";
		}
		HttpSession s = SessionManager.session();
		Pet p = (Pet) s.getAttribute("pet");
		pet.setId(p.getId());
		pet.setUser(p.getUser());
		this.petRepository.save(pet);
		return "redirect:/";
}


	@ModelAttribute("userPets")
	public List<Pet> getUserPets() {
		HttpSession s = SessionManager.session();
		User user = (User) s.getAttribute("user");
		return this.petRepository.findByUserId(user.getId());
	}

	@ModelAttribute("logedUser")
	public User getLogedUser() {
		HttpSession s = SessionManager.session();
		User user = (User) s.getAttribute("user");
		return user;
	}
	
	@ModelAttribute("availableHotels")
	public List<Hotel> getHotels() {
		return this.hotelRepository.findAll();
	}

}
