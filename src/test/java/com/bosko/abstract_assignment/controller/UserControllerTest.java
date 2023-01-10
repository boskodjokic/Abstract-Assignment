package com.bosko.abstract_assignment.controller;

import com.bosko.abstract_assignment.entity.Request;
import com.bosko.abstract_assignment.entity.User;
import com.bosko.abstract_assignment.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;

import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    MockMvc mockMvc;
    /**
     * Necessary set up before all the tests.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void isAccessApprovedWorkflow1() throws Exception {
        User user = User.builder().id(1L).name("user1").password("1234").role("ADMIN").build();
        Request request = Request.builder().id(1L).ipAddress("100.100.100.100").path("/admin/").build();
        user.setRequest(request);
        request.setUser(user);

        String authorization = user.getName() + ":" + user.getPassword();
        when(userService.isAccessApproved(user.getName(), 1)).thenReturn(true);

        mockMvc.perform(get("/api/v1/admin/*")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("authorization", "Basic " + new String(encodeBase64(authorization.getBytes(StandardCharsets.UTF_8)))))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

    }

    @Test
    void isAccessApprovedWorkflow2() throws Exception {
        User user = User.builder().id(1L).name("user3").password("qwerty").role("SUPER_ADMIN").build();
        Request request = Request.builder().id(1L).ipAddress("100.100.100.1").path("/admin/").build();
        user.setRequest(request);
        request.setUser(user);

        String authorization = user.getName() + ":" + user.getPassword();
        when(userService.isAccessApproved(user.getName(), 2)).thenReturn(true);

        mockMvc.perform(get("/api/v2/admin/*")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("authorization", "Basic " + new String(encodeBase64(authorization.getBytes(StandardCharsets.UTF_8)))))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}