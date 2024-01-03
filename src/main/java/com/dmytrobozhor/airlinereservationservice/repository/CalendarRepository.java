package com.dmytrobozhor.airlinereservationservice.repository;

import com.dmytrobozhor.airlinereservationservice.domain.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Date> {

    @Query(nativeQuery = true,
            value = "select * from calendar " +
                    "where day_date = :#{#calendar.getDate()} " +
                    "and business_day = :#{#calendar.getBusinessDay()}")
    Optional<Calendar> findByAllFields(Calendar calendar);

}
