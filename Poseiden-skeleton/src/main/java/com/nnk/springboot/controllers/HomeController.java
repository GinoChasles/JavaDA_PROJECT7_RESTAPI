package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.CustomUserDetails;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController
{
	private final UserRepository userRepository;

	public HomeController(final UserRepository userRepositoryParam) {
		userRepository = userRepositoryParam;
	}

	@RequestMapping("/")
	public String home(Model model)
	{
		return "home";
	}

	@RequestMapping("/admin/home")
	public ModelAndView adminHome(HttpServletRequest request,
								  @AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		User loggedInUser = userRepository.findUserByUsername(userDetail.getUsername()).get();
		model.addAttribute("loggedInUser", loggedInUser);
		request.getSession().setAttribute("userId", loggedInUser.getId());
		return new ModelAndView("redirect:/bidList/list");
	}


}
