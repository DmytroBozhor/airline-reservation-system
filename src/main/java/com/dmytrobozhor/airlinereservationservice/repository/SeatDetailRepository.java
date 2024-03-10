package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.SeatDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatDetailRepository extends JpaRepository<SeatDetail, Long> {

}
