package com.nnk.springboot.controller;

import com.nnk.springboot.Application;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
import com.nnk.springboot.service.CustomOAuth2User;
import com.nnk.springboot.service.CustomUserDetailsService;
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

//@ExtendWith(SpringExtension.class)
//@WebMvcTest(CurveController.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class CurveControllerTest {


    @Autowired
    private MockMvc mvc;
    @MockBean
    private CurvePointService curveService;
    @MockBean
    private CustomUserDetailsService userDetailService;

    @MockBean
    private CustomOAuth2User oAuth2UserService;
    @Autowired
    private WebApplicationContext context;
    private CurvePoint curvePoint;
    private List<CurvePoint> curvePointList = new ArrayList<>();

    @BeforeEach
    void setup() {
        curvePoint = new CurvePoint(10, 10d, 30d);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        curvePointList.add(curvePoint);
    }

    @Test
    void getCurvePointTest_shouldReturnCurvePointView() throws Exception {
        //ARRANGE
        when(curveService.findAll()).thenReturn(curvePointList);
        //ACT
        mvc.perform(get("/curvePoint/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("curvePointList"))
                .andExpect(view().name("curvePoint/list"));
    }

    @Test
    void addCurveFormTest_shouldReturnAddCurvePointView() throws Exception {
        //ACT
        mvc.perform(get("/curvePoint/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }

    @Test
    void validateCurvePoint_withInvalidCurvePoint_shouldReturnErrorViewAddCurvePoint() throws Exception {
        //ARRANGE
        when(curveService.insert(curvePoint)).thenReturn(curvePointList);
        //ACT
        mvc.perform(post("/curvePoint/validate")
                        .sessionAttr("curvePoint", curvePoint)
                        .param("curveId", String.valueOf(curvePoint.getCurveId()))
                        .param("term", String.valueOf(curvePoint.getTerm()))
                        .param("value", "-10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "must be positive or equals to 0")))
                .andExpect(view().name("curvePoint/add"));
    }

    @Test
    void validateCurvePoint_shouldAddCurvePoint_AndReturnTOCurvePointView() throws Exception {
        //ARRANGE
        when(curveService.insert(curvePoint)).thenReturn(curvePointList);
        //ACT
        mvc.perform(post("/curvePoint/validate")
                        .sessionAttr("curvePoint", curvePoint)
                        .param("curveId", String.valueOf(curvePoint.getCurveId()))
                        .param("term", String.valueOf(curvePoint.getTerm()))
                        .param("value", String.valueOf(curvePoint.getValue()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/curvePoint/list"));

    }

    @Test
    void UpdateCurvePoint_shouldUpdateCurvePoint_AndReturnTOCurvePointView() throws Exception {
        //ARRANGE
        when(curveService.insert(curvePoint)).thenReturn(curvePointList);

        //ACT
        mvc.perform(post("/curvePoint/update/1")
                        .sessionAttr("curvePoint", curvePoint)
                        .param("curveId", String.valueOf(curvePoint.getCurveId()))
                        .param("term", String.valueOf(curvePoint.getTerm()))
                        .param("value", String.valueOf(curvePoint.getValue()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/curvePoint/list"));

    }

    @Test
    void updateCurvePoint_withInvalidCurvePoint_shouldReturnErrorInViewCurvePoint() throws Exception {
        //ARRANGE
        when(curveService.insert(curvePoint)).thenReturn(curvePointList);

        //ACT
        mvc.perform(post("/curvePoint/update/1")
                        .sessionAttr("curvePoint", curvePoint)
                        .param("curveId", String.valueOf(curvePoint.getCurveId()))
                        .param("term", String.valueOf(curvePoint.getTerm()))
                        .param("value", "-10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "must be positive or equals to 0")))
                .andExpect(view().name("curvePoint/update"));
    }

    @Test
    void showUpdateFormCurvePointTest_shouldReturnUpdateCurvePointView() throws Exception {
        //ARRANGE
        when(curveService.getCurvePointById(1)).thenReturn(curvePoint);
        //ACT
        mvc.perform(get("/curvePoint/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("curvePoint"))
                .andExpect(view().name("curvePoint/update"));
    }

    @Test
    void deleteCurvePointTest_shouldReturnToCurvePointView() throws Exception {
        //ARRANGE
        when(curveService.getCurvePointById(1)).thenReturn(curvePoint);
        when(curveService.insert(curvePoint)).thenReturn(curvePointList);
        //ACT
        mvc.perform(get("/curvePoint/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/curvePoint/list"));
    }
}