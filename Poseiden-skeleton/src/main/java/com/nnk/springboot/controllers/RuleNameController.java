package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
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
public class RuleNameController {
    // TODO: Inject RuleName service
    private final RuleNameService ruleNameService;
    private static final Logger logger = LoggerFactory.getLogger(RuleNameController.class);

    public RuleNameController(final RuleNameService ruleNameServiceParam) {
        ruleNameService = ruleNameServiceParam;
    }

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        // TODO: find all RuleName, add to model
        logger.info("starting get rating list");

        List<RuleName> ruleNameListLocal = ruleNameService.findAll();
        model.addAttribute("ruleNameList", ruleNameListLocal);
        logger.info("go to ruleName list view");

        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        logger.info("starting get add view");

        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return RuleName list
        logger.info("starting post rating");
        if (result.hasErrors()) {
            logger.info("field error");
            return "ruleName/add";
        }
        List<RuleName> ruleNameListLocal = ruleNameService.insert(ruleName);
        model.addAttribute("ruleNameList", ruleNameListLocal);
        logger.info("return to ruleName list view");

        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get RuleName by Id and to model then show to the form
        logger.info("starting get rating to update ");

        RuleName ruleNameLocal = ruleNameService.getRuleNamById(id);
        model.addAttribute("ruleName", ruleNameLocal);
        logger.info("return to ruleName update view");

        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list
        logger.info("starting update rating");
        if (result.hasErrors()) {
            logger.info("field error");
            ruleName.setId(id);
            return "ruleName/update";
        }
        List<RuleName> ruleNameListLocal = ruleNameService.update(id, ruleName);
        model.addAttribute("ruleNameList", ruleNameListLocal);
        logger.info("return to ruleName list view");

        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list
        logger.info("starting deleting rating");

        List<RuleName> ruleNameListLocal = ruleNameService.delete(id);
        model.addAttribute("ruleNameList", ruleNameListLocal);
        logger.info("return to ruleName list view");

        return "redirect:/ruleName/list";
    }
}
