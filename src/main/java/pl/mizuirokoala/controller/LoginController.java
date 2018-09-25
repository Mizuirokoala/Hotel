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
import pl.mizuirokoala.entity.User;
import pl.mizuirokoala.repository.HotelRepository;
import pl.mizuirokoala.repository.UserRepository;

@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	// user section
	@GetMapping("/login")
	public String loginUser(Model m) {
		m.addAttribute("loginData", new LoginData());
		return "login/login_user";
	}
	
	@GetMapping("/register")
	public String registerUser(Model m) {
		m.addAttribute("user", new User());
		return "login/register_user";
	}
	
	@PostMapping("/register")
	public String registerPost(@Valid @ModelAttribute User user, BindingResult bindingResult, Model m) {
		if(bindingResult.hasErrors()) {
			return "login/register_user";
		}
		try {			
			this.userRepository.save(user);
			HttpSession s = SessionManager.session();
			s.setAttribute("user", user);
			return "redirect:/";
		} catch (Exception e) {
			m.addAttribute("mailError", "The email is already used. Log in or use different email.");
			return "login/register_user";
		}
	}
	
	@PostMapping("/login")
	public String loginPost(@Valid @ModelAttribute LoginData loginData, Model m, BindingResult bindingResult) {
		User u = this.userRepository.findOneByEmail(loginData.getEmail());
			if (u != null && u.isPasswordCorrect(loginData.getPassword())) {
				HttpSession s = SessionManager.session();
				s.setAttribute("user", u);
				return "redirect:/";
			}
			m.addAttribute("msg", "Enter valid data");
			return "login/login_user";
	}
	
	@GetMapping("/logout")
	public String logout(Model m) {
		HttpSession s = SessionManager.session();
		s.invalidate();
		return "redirect:/";
	}
	
	@ModelAttribute("availableHotels")
	public List<Hotel> getHotels() {
		return this.hotelRepository.findAll();
	}
}
