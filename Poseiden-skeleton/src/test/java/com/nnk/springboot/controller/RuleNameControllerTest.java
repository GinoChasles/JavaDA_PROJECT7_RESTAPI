package com.nnk.springboot.controller;

import com.nnk.springboot.Application;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.CustomOAuth2User;
import com.nnk.springboot.service.CustomUserDetailsService;
import com.nnk.springboot.service.RuleNameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class RuleNameControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private RuleNameService ruleNameService;
    @MockBean
    private CustomUserDetailsService userDetailService;

    @MockBean
    private CustomOAuth2User oAuth2UserService;
    @Autowired
    private WebApplicationContext context;
    private RuleName ruleName;
    private List<RuleName> ruleNameList = new ArrayList<>();

    @BeforeEach
    void setup() {
        ruleName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        ruleNameList.add(ruleName);
    }

    @Test
    void getRuleNameTest_shouldReturnRuleNameView() throws Exception {
        //ARRANGE
        when(ruleNameService.findAll()).thenReturn(ruleNameList);
        //ACT
        mvc.perform(get("/ruleName/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ruleNameList"))
                .andExpect(view().name("ruleName/list"));
    }

    @Test
    void addRuleNameFormTest_shouldReturnAddRuleNameView() throws Exception {
        //ACT
        mvc.perform(get("/ruleName/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));
    }

    @Test
    void validateRuleName_withInvalidRuleName_shouldReturnErrorViewAddRuleName() throws Exception {
        //ARRANGE
        when(ruleNameService.insert(ruleName)).thenReturn(ruleNameList);
        //ACT
        mvc.perform(post("/ruleName/validate")
                        .sessionAttr("ruleName", ruleName)
                        .param("name", "")
                        .param("description", ruleName.getDescription())
                        .param("json", ruleName.getJson())
                        .param("template", ruleName.getTemplate())
                        .param("sqlStr", ruleName.getSqlStr())
                        .param("sqlPart", ruleName.getSqlStr())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "Le nom est requis")))
                .andExpect(view().name("ruleName/add"));
    }

    @Test
    void validateRuleName_shouldAddRuleName_AndReturnToRuleNameView() throws Exception {
        //ARRANGE
        when(ruleNameService.insert(ruleName)).thenReturn(ruleNameList);
        //ACT
        mvc.perform(post("/ruleName/validate")
                        .sessionAttr("ruleName", ruleName)
                        .param("name", ruleName.getName())
                        .param("description", ruleName.getDescription())
                        .param("json", ruleName.getJson())
                        .param("template", ruleName.getTemplate())
                        .param("sqlStr", ruleName.getSqlStr())
                        .param("sqlPart", ruleName.getSqlStr())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/ruleName/list"));

    }

    @Test
    void UpdateRuleName_shouldUpdateRuleName_AndReturnTORuleNameView() throws Exception {
        //ARRANGE
        when(ruleNameService.insert(ruleName)).thenReturn(ruleNameList);
        //ACT
        mvc.perform(post("/ruleName/update/1")
                        .sessionAttr("ruleName", ruleName)
                        .param("name", ruleName.getName())
                        .param("description", ruleName.getDescription())
                        .param("json", ruleName.getJson())
                        .param("template", ruleName.getTemplate())
                        .param("sqlStr", ruleName.getSqlStr())
                        .param("sqlPart", ruleName.getSqlStr())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/ruleName/list"));

    }

    @Test
    void updateRuleName_withInvalidRuleName_shouldReturnErrorInViewRuleName() throws Exception {
        //ARRANGE
        when(ruleNameService.insert(ruleName)).thenReturn(ruleNameList);
        //ACT
        mvc.perform(post("/ruleName/update/1")
                        .sessionAttr("ruleName", ruleName)
                        .param("name", "")
                        .param("description", ruleName.getDescription())
                        .param("json", ruleName.getJson())
                        .param("template", ruleName.getTemplate())
                        .param("sqlStr", ruleName.getSqlStr())
                        .param("sqlPart", ruleName.getSqlStr())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "Le nom est requis")))
                .andExpect(view().name("ruleName/update"));
    }

    @Test
    void showUpdateFormRuleNameTest_shouldReturnUpdateRuleNameView() throws Exception {
        //ARRANGE
        when(ruleNameService.getRuleNamById(1)).thenReturn(ruleName);
        //ACT
        mvc.perform(get("/ruleName/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ruleName"))
                .andExpect(view().name("ruleName/update"));
    }

    @Test
    void deleteRuleNameTest_shouldReturnToRuleNameView() throws Exception {
        //ARRANGE
        when(ruleNameService.getRuleNamById(1)).thenReturn(ruleName);
        when(ruleNameService.delete(1)).thenReturn(ruleNameList);
        //ACT
        mvc.perform(get("/ruleName/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/ruleName/list"));
    }
}