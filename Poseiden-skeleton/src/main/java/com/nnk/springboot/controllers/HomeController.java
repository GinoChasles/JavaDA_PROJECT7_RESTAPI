package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.security.CustomUserDetails;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public HomeController(final UserRepository userRepositoryParam) {
		userRepository = userRepositoryParam;
	}

	@RequestMapping("/")
	public String home(Model model)
	{
		logger.info("get home view");

		return "home";
	}

	@RequestMapping("/admin/home")
	public ModelAndView adminHome(HttpServletRequest request,
								  @AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
		logger.info("get admin view");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		User loggedInUser = userRepository.findUserByUsername(userDetail.getUsername()).get();
		model.addAttribute("loggedInUser", loggedInUser);
		request.getSession().setAttribute("userId", loggedInUser.getId());
		logger.info("return to bid list view");
		return new ModelAndView("redirect:/bidList/list");
	}


}
