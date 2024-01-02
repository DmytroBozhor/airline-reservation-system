package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.Airport;
import com.dmytrobozhor.airlinereservationservice.domain.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, Integer> {

    //    TODO: find out if it is needed no use paymentStatus.getStatus().toString()
    @Query(nativeQuery = true,
            value = "select * from payment_status " +
                    "where status_yn = :#{#paymentStatus.getStatus()} " +
                    "and due_date = :#{#paymentStatus.getDueDate()} " +
                    "and amount = :#{#paymentStatus.getAmount()} " +
                    "and reservation_id = :#{#paymentStatus.getReservation().getId()}")
    Optional<PaymentStatus> findByAllFields(PaymentStatus paymentStatus);

}
