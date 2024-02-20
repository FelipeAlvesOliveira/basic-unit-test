package org.unit.test.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.unit.test.entity.Car;

// Entidade que armazena estados, cria-se somente uma entidade e reutiliza ele.
@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {
}
