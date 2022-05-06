package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RatingController {
    // TODO: Inject Rating service
    private final RatingService ratingService;
    private static final Logger logger = LoggerFactory.getLogger(RatingController.class);

    public RatingController(final RatingService ratingServiceParam) {
        ratingService = ratingServiceParam;
    }

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        // TODO: find all Rating, add to model
        logger.info("starting get rating list");

        List<Rating> ratingListLocal = ratingService.findAll();
        model.addAttribute("ratingList", ratingListLocal);
        logger.info("go to rating list view");

        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        logger.info("starting get rating add view");

        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Rating list
        logger.info("starting post rating");

        List<Rating> ratingListLocal = ratingService.insert(rating);
        model.addAttribute("ratingList", ratingListLocal);
        logger.info("return to rating list view");

        return "redirect:/rating/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Rating by Id and to model then show to the form
        logger.info("starting get rating to update");

        Rating ratingLocal = ratingService.getRatingById(id);
        model.addAttribute("rating", ratingLocal);
        logger.info("return to update rating view");

        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Rating and return Rating list
        logger.info("starting update rating");

        List<Rating> ratingListLocal = ratingService.update(id, rating);
        model.addAttribute("ratingList", ratingListLocal);
        logger.info("return to rating list view");

        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Rating by Id and delete the Rating, return to Rating list
        logger.info("starting deleting rating view");

        List<Rating> ratingListLocal = ratingService.delete(id);
        model.addAttribute("ratingList", ratingListLocal);
        logger.info("return to rating list view");

        return "redirect:/rating/list";
    }
}
