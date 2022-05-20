package com.nnk.springboot.controller;

import com.nnk.springboot.Application;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.CustomOAuth2User;
import com.nnk.springboot.service.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private CustomUserDetailsService userDetailService;

    @MockBean
    private CustomOAuth2User oAuth2UserService;
    @Autowired
    private WebApplicationContext context;
    private User user;
    private List<User> userList = new ArrayList<>();

    @BeforeEach
    void setup() {
        user = new User("admin", "$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa", "admin", "ADMIN");
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        userList.add(user);
    }

    @Test
    void getUserTest_shouldReturnUserView() throws Exception {
        //ARRANGE
        when(userRepository.findAll()).thenReturn(userList);
        //ACT
        mvc.perform(get("/user/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("users"))
                .andExpect(view().name("user/list"));
    }

    @Test
    void addUserFormTest_shouldReturnAddUserView() throws Exception {
        //ACT
        mvc.perform(get("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"));
    }

    @Test
    void validateUser_withInvalidUser_shouldReturnErrorViewAddUser() throws Exception {
        //ARRANGE
        when(userRepository.save(user)).thenReturn(user);

        //ACT
        mvc.perform(post("/user/validate")
                        .sessionAttr("user", user)
                        .param("username", user.getUsername())
                        .param("password", user.getPassword())
                        .param("fullname", user.getFullname())
                        .param("role", "")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "Role is mandatory")))
                .andExpect(view().name("user/add"));
    }

    @Test
    void validateUser_shouldAddUser_AndReturnTOUserView() throws Exception {
        //ARRANGE
        when(userRepository.save(user)).thenReturn(user);
        //ACT
        mvc.perform(post("/user/validate")
                        .sessionAttr("user", user)
                        .param("username", user.getUsername())
                        .param("password", "TestMdp123!")
                        .param("fullname", user.getFullname())
                        .param("role", user.getRole())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/user/list"));

    }

    @Test
    void UpdateUser_shouldUpdateUser_AndReturnTOUserView() throws Exception {
        //ARRANGE
        when(userRepository.save(user)).thenReturn(user);
        //ACT
        mvc.perform(post("/user/update/1")
                        .sessionAttr("user", user)
                        .param("username", user.getUsername())
                        .param("password", "TestMdp123!")
                        .param("fullname", user.getFullname())
                        .param("role", user.getRole())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/user/list"));

    }

    @Test
    void updateUser_withInvalidUser_shouldReturnErrorInViewUser() throws Exception {
        //ARRANGE
        when(userRepository.save(user)).thenReturn(user);
        //ACT
        mvc.perform(post("/user/update/1")
                        .sessionAttr("user", user)
                        .param("username", user.getUsername())
                        .param("password", "password")
                        .param("fullname", user.getFullname())
                        .param("role", user.getRole())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "Votre mot de passe doit contenir au moins une lettre majuscule, au moins 8 caractères, au moins un chiffre et un symbole")))
                .andExpect(view().name("user/update"));
    }

    @Test
    void showUpdateFormUserTest_shouldReturnUpdateUserView() throws Exception {
        //ARRANGE
        when(userRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(user));
        //ACT
        mvc.perform(get("/user/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("user/update"));
    }

    @Test
    void deleteUserTest_shouldReturnToUserView() throws Exception {
        //ARRANGE
        when(userRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(user));
        doNothing().when(userRepository).delete(user);
        //ACT
        mvc.perform(get("/user/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //ASSERT
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/user/list"));
    }
}