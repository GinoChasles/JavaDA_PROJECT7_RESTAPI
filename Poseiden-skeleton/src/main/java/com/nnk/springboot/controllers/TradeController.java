package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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
public class TradeController {
    // TODO: Inject Trade service
    private final TradeService tradeService;
    private static final Logger logger = LoggerFactory.getLogger(TradeController.class);

    public TradeController(final TradeService tradeServiceParam) {
        tradeService = tradeServiceParam;
    }

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        // TODO: find all Trade, add to model
        logger.info("starting get trade list view");

        List<Trade> tradeListLocal = tradeService.findAll();
        model.addAttribute("tradeList", tradeListLocal);
        logger.info("go to trade list view");

        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        logger.info("starting get trade add view");

        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Trade list
        logger.info("starting post trade");

        List<Trade> tradeListLocal = tradeService.insert(trade);
        model.addAttribute("tradeList", tradeListLocal);
        logger.info("return to trade list view");

        return "redirect:/trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Trade by Id and to model then show to the form
        logger.info("starting get trade to update");

        Trade tradeLocal = tradeService.getTradeById(id);
        model.addAttribute("trade", tradeLocal);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Trade and return Trade list
        logger.info("starting updating trade");

        List<Trade> tradeLocal = tradeService.update(id, trade);
        model.addAttribute("tradeList", tradeLocal);
        logger.info("return to trade list view");

        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Trade by Id and delete the Trade, return to Trade list
        logger.info("starting deleting trade");

        List<Trade> tradeLocal = tradeService.delete(id);
        model.addAttribute("tradeList", tradeLocal);
        logger.info("return to trade list view");

        return "redirect:/trade/list";
    }
}
