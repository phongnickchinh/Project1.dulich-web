package com.pweb.dulich.service.impl;

import com.pweb.dulich.model.AdviceRequest;
import com.pweb.dulich.repository.AdviceRequestRepository;
import com.pweb.dulich.service.AdviceRequestService;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class AdviceRequestImpl implements AdviceRequestService {
    private AdviceRequestRepository adviceRequestRepository;

    public AdviceRequestImpl(AdviceRequestRepository adviceRequestRepository) {
        this.adviceRequestRepository = adviceRequestRepository;
    }


    @Override
    public Boolean createAdviceRequest(AdviceRequest adviceRequest) {
        adviceRequest.setRequestTime(new Date());
        adviceRequest.setStatus(0);
        adviceRequestRepository.save(adviceRequest);
        return true;
    }

    @Override
    public List<AdviceRequest> getAllAdviceRequest() {

        return adviceRequestRepository.findAll();
    }
    
}
