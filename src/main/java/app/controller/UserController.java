package app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import app.entity.CustomUser;
import app.repository.CustomUserRepository;
import utils.PasswordEncoder;

@Controller
@Transactional
public class UserController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired(required = true)
	private CustomUserRepository userRepository;
	
	@RequestMapping(value = REQUEST_MAPPING_USERS, method = RequestMethod.GET)
	public String getAllUsers(Model model) {
		logger.info("Entering to the getAllUsers() method***");
		model.addAttribute("users", userRepository.findAll());
		return "user/getAll";
	}
	
	@RequestMapping(value = REQUEST_MAPPING_USER, params = PARAM_ADD, method = RequestMethod.GET)
	public String getAddUser() {
		logger.info("//////////////// Entering to the getAddUser() method ... ////////////////");
		return "user/add";
	}
	
	@RequestMapping(value = REQUEST_MAPPING_USER, params = PARAM_EDIT, method = RequestMethod.GET)
	public String getEditUser(@RequestParam("name") String username, Model model) {
		logger.info("//////////////// Entering to the getEditUser() method ... ////////////////");
		model.addAttribute("user", userRepository.findByUsername(username));
		return "user/edit";
	}
	
	@RequestMapping(value = REQUEST_MAPPING_USER, method = RequestMethod.GET)
	public String getViewUser(@RequestParam("name") String username, Model model) {
		logger.info("//////////////// Entering to the getEditUser() method ... ////////////////");
		model.addAttribute("user", userRepository.findByUsername(username));
		return "user/edit";
	}
	
	@RequestMapping(value = REQUEST_MAPPING_USER, params = PARAM_ADD, method = RequestMethod.POST)
	public String postAddUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String username, 
			@RequestParam String password, @RequestParam String email, @RequestParam String role,
			@RequestParam boolean enabled) {
		logger.info("<== Enter to 'postAddUser()' method ... ==>");
		logger.info("<== Adding new 'User' ==>");
		password = PasswordEncoder.getEncodedPassword(password);
		CustomUser user = new CustomUser(firstName, lastName, username, password, email, role, enabled);
		user = userRepository.save(user);
		logger.info("<== Saving new 'User' with ID=" + user.getId() + " was successeful ==>");
		logger.info("<== Out of 'postAddUser()' method ... ==>");
		return "user/getAll";
	}

}
