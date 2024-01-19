package com.pweb.dulich.service;

import java.util.List;

import com.pweb.dulich.model.AdviceRequest;

public interface AdviceRequestService {
    public Boolean createAdviceRequest(AdviceRequest adviceRequest);
    public List<AdviceRequest> getAllAdviceRequest();
    
}
