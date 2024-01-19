package com.pweb.dulich.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pweb.dulich.model.BookingRequest;


@Repository
public interface BookingRequestRepository extends JpaRepository<BookingRequest,Long>{
}
