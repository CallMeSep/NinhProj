package hotel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hotel.entity.UserRole;
import hotel.repository.UserRoleRepository;


@Controller
public class UserController {
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserController(UserRoleRepository userRoleRepository) {
		this.userRoleRepository = userRoleRepository;
	}

	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String loginPage(HttpServletRequest req,Model model) {
		String referer = req.getHeader("Referer");
		req.getSession().setAttribute("url_prior_login", referer);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		
		return "redirect:/" + referer;
	}

	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		UserRole userRole = new UserRole();
		model.addAttribute("userRole", userRole);
		model.addAttribute("message", "");
		return "register";
	}

	@PostMapping("/register")
	public String postRegisterPage(@ModelAttribute("userRole") UserRole userRole, Errors errors, Model model) {
		userRole.setRole("ROLE_USER");
		userRole.setPassword(passwordEncoder.encode(userRole.getPassword()));
		if (errors.hasErrors() || userRoleRepository.findByUsername(userRole.getUsername()).isPresent()) {
			model.addAttribute("message", "username already exist");
			return "register";
		} else {
			userRoleRepository.save(userRole);
			return "redirect:/login";
			
		}

	}
}
