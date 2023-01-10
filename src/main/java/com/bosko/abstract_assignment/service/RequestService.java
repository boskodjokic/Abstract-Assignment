package com.bosko.abstract_assignment.service;

import com.bosko.abstract_assignment.repository.RequestRepository;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    private RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }
}
