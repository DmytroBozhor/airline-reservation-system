package com.dmytrobozhor.airlinereservationservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
// By default:
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class AirlineReservationServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
