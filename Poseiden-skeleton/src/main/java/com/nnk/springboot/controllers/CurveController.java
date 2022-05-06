package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
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
public class CurveController {
    // TODO: Inject Curve Point service
    private final CurvePointService curvePointService;
    private static final Logger logger = LoggerFactory.getLogger(CurveController.class);


    public CurveController(
        final CurvePointService curvePointServiceParam) {
        curvePointService = curvePointServiceParam;
    }

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        // TODO: find all Curve Point, add to model
        logger.info("starting get curvpoint list");

        List<CurvePoint> curvePointListLocal = curvePointService.findAll();
        model.addAttribute("curvePointList", curvePointListLocal);
        logger.info("go to curvePoint list view");

        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        logger.info("starting get curvpoint add view");

        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Curve list
        logger.info("starting post curvpoint");
        if (result.hasErrors()) {
            logger.info("field error");
            return "curvePoint/add";
        }
        List<CurvePoint> curvePointListLocal = curvePointService.insert(curvePoint);
        model.addAttribute("curvePointList", curvePointListLocal);
        logger.info("return to curvePoint list view");

        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get CurvePoint by Id and to model then show to the form
        logger.info("starting get curvpoint to update");

        CurvePoint curvePointLocal = curvePointService.getCurvePointById(id);
        model.addAttribute("curvePoint", curvePointLocal);
        logger.info("return update curvpoint view");

        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Curve and return Curve list
        logger.info("starting updating curvpoint");

        if (result.hasErrors()) {
            logger.info("field error");
            curvePoint.setId(id);
            return "curvePoint/update";
        }
       List<CurvePoint> curvePointListLocal = curvePointService.update(id, curvePoint);
       model.addAttribute("curvePointList", curvePointListLocal);
        logger.info("return to curvePoint list view");

        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Curve by Id and delete the Curve, return to Curve list
        logger.info("starting deleting curvePoint");

        List<CurvePoint> curvePointListLocal = curvePointService.delete(id);
        model.addAttribute("curvePointList", curvePointListLocal);
        logger.info("return to curvePoint list view");

        return "redirect:/curvePoint/list";
    }
}
