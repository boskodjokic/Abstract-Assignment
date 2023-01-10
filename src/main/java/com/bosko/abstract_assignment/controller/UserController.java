package com.bosko.abstract_assignment.controller;

import com.bosko.abstract_assignment.service.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "/api/";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "v1/admin/*")
    public boolean isAccessApprovedWorkflow1(HttpServletRequest request) {
        return userService.isAccessApproved(getUserFromRequest(request), 1);
    }

    @GetMapping(value = "v2/admin/*")
    public boolean isAccessApprovedWorkflow2(HttpServletRequest request) {
        return userService.isAccessApproved(getUserFromRequest(request), 2);
    }

    /**
     * Helper method to extract the userName from http request
     * @param request HTTP request sent
     * @return userName
     */
    private String getUserFromRequest(final HttpServletRequest request) {
        return new String(Base64.decodeBase64(request.getHeader("authorization").substring(6))).split(":")[0];
    }
}
