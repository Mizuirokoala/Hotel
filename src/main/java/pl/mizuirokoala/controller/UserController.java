package pl.mizuirokoala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.mizuirokoala.repository.PetRepository;
import pl.mizuirokoala.repository.UserRepository;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PetRepository petRepository;
	
	//TODO add edit user option
}
