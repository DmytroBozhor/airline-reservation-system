package com.dmytrobozhor.airlinereservationservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "calendar")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calendar {

    @Id
    @Column(name = "day_date")
    private Date date;

    @Column(name = "business_day", nullable = false)
    private Boolean businessDay;

}
