package com.pweb.dulich.repository;

import com.pweb.dulich.model.AdviceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdviceRequestRepository extends JpaRepository<AdviceRequest,Long>{
        
}
