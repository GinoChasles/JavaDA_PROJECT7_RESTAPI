package com.nnk.springboot.controller;

import com.nnk.springboot.Application;
import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@ExtendWith(SpringExtension.class)
//@WebMvcTest(BidListController.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class BidListControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private BidListService bidService;
    @MockBean
    private CustomUserDetailsService userDetailService;

    @MockBean
    private CustomOAuth2User oAuth2UserService;
    @Autowired
    private WebApplicationContext context;
    private BidList bid;
    private List<BidList> bidListList = new ArrayList<>();

    @BeforeEach
    void setup() {
        bid = new BidList("Account Test", "TypeTest", 10d);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        bidListList.add(bid);
    }

    @Test
    void getBidListTest_shouldReturnBidListView() throws Exception {
        //ARRANGE
        when(bidService.findAll()).thenReturn(Arrays.asList(bid));
        //ACT
        mvc.perform(get("/bidList/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bidList"))
                .andExpect(view().name("bidList/list"));
    }

    @Test
    void addBidFormTest_shouldReturnAddBidListView() throws Exception {
        //ACT
        mvc.perform(get("/bidList/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }

    @Test
    void validateBidList_withInvalidBidList_shouldViewAddBidList() throws Exception {
        //ARRANGE
        when(bidService.insert(bid)).thenReturn(bidListList);
        //ACT
        mvc.perform(post("/bidList/validate")
                        .sessionAttr("bidList", bid)
                        .param("account", bid.getAccount())
                        .param("type", bid.getType())
                        .param("bidQuantity", "-10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "must be positive or equals to 0")))
                .andExpect(view().name("bidList/add"));
    }

    @Test
    void validateBidList_shouldAddBidList_AndReturnTOBidListView() throws Exception {
        //ARRANGE

        when(bidService.insert(bid)).thenReturn(bidListList);
        //ACT
        mvc.perform(post("/bidList/validate")
                        .sessionAttr("bidList", bid)
                        .param("account", bid.getAccount())
                        .param("type", bid.getType())
                        .param("bidQuantity", String.valueOf(bid.getBidQuantity()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/bidList/list"));

    }

    @Test
    void UpdateBidList_shouldUpdateBidList_AndReturnTOBidListView() throws Exception {
        //ARRANGE
        when(bidService.insert(bid)).thenReturn(bidListList);
        //ACT
        mvc.perform(post("/bidList/update/1")
                        .sessionAttr("bidList", bid)
                        .param("account", bid.getAccount())
                        .param("type", bid.getType())
                        .param("bidQuantity", String.valueOf(bid.getBidQuantity()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/bidList/list"));

    }

    @Test
    void updateBidList_withInvalidBidList_shouldReturnErrorInViewBidList() throws Exception {
        //ARRANGE
        when(bidService.insert(bid)).thenReturn(bidListList);
        //ACT
        mvc.perform(post("/bidList/update/1")
                        .sessionAttr("bidList", bid)
                        .param("account", bid.getAccount())
                        .param("type", bid.getType())
                        .param("bidQuantity", "-10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "must be positive or equals to 0")))
                .andExpect(view().name("bidList/update"));
    }

    @Test
    void showUpdateFormBidListTest_shouldReturnUpdateBidListView() throws Exception {
        //ARRANGE
        when(bidService.getBidListById(1)).thenReturn(bid);
        //ACT
        mvc.perform(get("/bidList/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bidList"))
                .andExpect(view().name("bidList/update"));
    }

    @Test
    void deleteBidListTest_shouldReturnToBidListView() throws Exception {
        //ARRANGE
        when(bidService.getBidListById(1)).thenReturn(bid);
        when(bidService.delete(1)).thenReturn(bidListList);
        //ACT
        mvc.perform(get("/bidList/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/bidList/list"));
    }
}