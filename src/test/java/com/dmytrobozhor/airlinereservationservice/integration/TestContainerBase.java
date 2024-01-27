package com.dmytrobozhor.airlinereservationservice.integration;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public abstract class TestContainerBase {

    static final PostgreSQLContainer POSTGRE_SQL_CONTAINER;

    static {

        POSTGRE_SQL_CONTAINER = new PostgreSQLContainer("postgres:latest")
                .withUsername("postgres")
                .withDatabaseName("airline_reservation_test")
                .withPassword("1");

        POSTGRE_SQL_CONTAINER.start();
    }

    @DynamicPropertySource
    public static void dynamicPropertySource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRE_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRE_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRE_SQL_CONTAINER::getPassword);
    }

}
