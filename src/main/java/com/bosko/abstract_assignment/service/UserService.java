package com.bosko.abstract_assignment.service;

import com.bosko.abstract_assignment.entity.Request;
import com.bosko.abstract_assignment.entity.User;
import com.bosko.abstract_assignment.repository.RequestRepository;
import com.bosko.abstract_assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestRepository requestRepository;

    /**
     * Method for checking does the user have access to resource
     * @param userName userName from the database
     * @param workflow workflow for which we are checking
     * @return true or false
     */
    public boolean isAccessApproved(final String userName, final int workflow) {
        User user = userRepository.findByName(userName);

        String role = user.getRole();
        String ipAddress = null;
        Optional<Request> request = requestRepository.findById(user.getRequest().getId());
        if(request.isPresent()) {
            ipAddress = request.get().getIpAddress();
        }
        return ipAddress != null && workflow ==1 ? role.equals("ADMIN") : (role.equals("ADMIN") || role.equals("SUPER_ADMIN")) && validateIpAddress(ipAddress, workflow);
    }

    /**
     * Helper method to validate is the IP address the exact one or in the range of allowed
     * @param ipAddress IP Address that we are checking
     * @param workflow workflow for which we are checking
     * @return true or false
     */
    public static boolean validateIpAddress(final String ipAddress, final int workflow) {
        boolean valid = false;
        StringTokenizer t = new StringTokenizer(ipAddress, ".");
        int a = Integer.parseInt(t.nextToken());
        int b = Integer.parseInt(t.nextToken());
        int c = Integer.parseInt(t.nextToken());
        int d = Integer.parseInt(t.nextToken());
            if ((a >= 0 && a <= 100) && (b >= 0 && b <= 100)
                    && (c >= 0 && c <= 100) && (workflow == 1 ? (d == 100) : (d >= 0 && d <= 28)))
                valid = true;

        return valid;
    }
}
