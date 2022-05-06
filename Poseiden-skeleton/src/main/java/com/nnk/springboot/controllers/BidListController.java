package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
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
public class BidListController {
    private final BidListService bidListService;
    private static final Logger logger = LoggerFactory.getLogger(BidListController.class);

    public BidListController(final BidListService bidListServiceParam) {
        bidListService = bidListServiceParam;
    }

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        // TODO: call service find all bids to show to the view
        logger.info("request get bidList/list");
        List<BidList> bidListListLocal = bidListService.findAll();
        model.addAttribute("bidList", bidListListLocal);
        logger.info("bidList list found, return view");
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        logger.info("get add page view");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list
        logger.info("starting post bid");
        List<BidList> bidListListLocal = bidListService.insert(bid);
        model.addAttribute("bidList", bidListListLocal);
        logger.info("redirect to bid list view");
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form
        logger.info("starting get bid to update");
        BidList bidListLocal = bidListService.getBidListById(id);
        model.addAttribute("bidList", bidListLocal);
        logger.info("return to update bid view");
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return list Bid
        logger.info("starting perform update bid");
        List<BidList> bidListListLocal = bidListService.update(id, bidList);
        model.addAttribute("bidList", bidListListLocal);
        logger.info("return to bid list view");
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        logger.info("perform delete bid");
        List<BidList> bidListListLocal = bidListService.delete(id);
        model.addAttribute("bidList", bidListListLocal);
        logger.info("return to bid list view");
        return "redirect:/bidList/list";
    }
}
