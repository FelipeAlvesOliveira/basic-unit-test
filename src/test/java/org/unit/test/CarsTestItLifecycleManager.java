package org.unit.test;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Map;

public class CarsTestItLifecycleManager implements QuarkusTestResourceLifecycleManager {

    public static final PostgreSQLContainer POSTGRES = new PostgreSQLContainer<>("postgres:12.2")
            .withExposedPorts(5432)
            .withEnv("POSTGRES_USER", "cars")
            .withEnv("POSTGRES_PASSWORD", "cars")
            .withEnv("POSTGRES_DB", "cars");

    @Override
    public Map<String, String> start() {
        POSTGRES.start();
        return Map.of(
                "quarkus.datasource.jdbc.url", POSTGRES.getJdbcUrl(),
                "quarkus.datasource.username", POSTGRES.getUsername(),
                "quarkus.datasource.password", POSTGRES.getPassword(),
                "quarkus.hibernate-orm.database.generation", "drop-and-create",
                "quarkus.hibernate-orm.database.generation.create-schemas", "true"
        );
    }

    @Override
    public void stop() {
        if (POSTGRES != null && POSTGRES.isRunning()) {
            POSTGRES.stop();
        }
    }
}
