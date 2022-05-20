package com.nnk.springboot.controller;

import com.nnk.springboot.Application;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.CustomOAuth2User;
import com.nnk.springboot.service.CustomUserDetailsService;
import com.nnk.springboot.service.RatingService;
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
public class RatingControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private RatingService ratingService;
    @MockBean
    private CustomUserDetailsService userDetailService;

    @MockBean
    private CustomOAuth2User oAuth2UserService;;
    @Autowired
    private WebApplicationContext context;
    private Rating rating;
    private List<Rating> ratingList = new ArrayList<>();

    @BeforeEach
    void setup() {
        rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        ratingList.add(rating);
    }

    @Test
    void getRatingTest_shouldReturnRatingView() throws Exception {
        //ARRANGE
        when(ratingService.findAll()).thenReturn(ratingList);
        //ACT
        mvc.perform(get("/rating/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ratingList"))
                .andExpect(view().name("rating/list"));
    }

    @Test
    void addRatingFormTest_shouldReturnAddRatingView() throws Exception {
        //ACT
        mvc.perform(get("/rating/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));
    }

    @Test
    void validateRating_withInvalidRating_shouldReturnErrorViewAddRating() throws Exception {
        //ARRANGE
        when(ratingService.insert(rating)).thenReturn(ratingList);
        //ACT
        mvc.perform(post("/rating/validate")
                        .sessionAttr("rating", rating)
                        .param("moodysRating", rating.getMoodysRating())
                        .param("sandPRating", rating.getSandPRating())
                        .param("fitchRating", "-10")
                        .param("orderNumber", "-10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "must be positive or equals to 0")))
                .andExpect(view().name("rating/add"));
    }

    @Test
    void validateRating_shouldAddRating_AndReturnTORatingView() throws Exception {
        //ARRANGE
        when(ratingService.insert(rating)).thenReturn(ratingList);
        //ACT
        mvc.perform(post("/rating/validate")
                        .sessionAttr("rating", rating)
                        .param("moodysRating", rating.getMoodysRating())
                        .param("sandPRating", rating.getSandPRating())
                        .param("fitchRating", String.valueOf(rating.getFitchRating()))
                        .param("orderNumber", String.valueOf(rating.getOrderNumber()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/rating/list"));

    }

    @Test
    void UpdateRating_shouldUpdateRating_AndReturnTORatingView() throws Exception {
        //ARRANGE
        when(ratingService.insert(rating)).thenReturn(ratingList);
        //ACT
        mvc.perform(post("/rating/update/1")
                        .sessionAttr("rating", rating)
                        .param("moodysRating", rating.getMoodysRating())
                        .param("sandPRating", rating.getSandPRating())
                        .param("fitchRating", String.valueOf(rating.getFitchRating()))
                        .param("orderNumber", String.valueOf(rating.getOrderNumber()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/rating/list"));

    }

    @Test
    void updateRating_withInvalidRating_shouldReturnErrorInViewRating() throws Exception {
        //ARRANGE
        when(ratingService.insert(rating)).thenReturn(ratingList);
        //ACT
        mvc.perform(post("/rating/update/1")
                        .sessionAttr("rating", rating)
                        .param("moodysRating", rating.getMoodysRating())
                        .param("sandPRating", rating.getSandPRating())
                        .param("fitchRating", "-10")
                        .param("orderNumber", "-10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "must be positive or equals to 0")))
                .andExpect(view().name("rating/update"));
    }

    @Test
    void showUpdateFormRatingTest_shouldReturnUpdateRatingView() throws Exception {
        //ARRANGE
        when(ratingService.getRatingById(1)).thenReturn(rating);
        //ACT
        mvc.perform(get("/rating/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("rating"))
                .andExpect(view().name("rating/update"));
    }

    @Test
    void deleteRatingTest_shouldReturnToRatingView() throws Exception {
        //ARRANGE
        when(ratingService.getRatingById(1)).thenReturn(rating);
        when(ratingService.delete(1)).thenReturn(ratingList);
        //ACT
        mvc.perform(get("/rating/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/rating/list"));
    }
}