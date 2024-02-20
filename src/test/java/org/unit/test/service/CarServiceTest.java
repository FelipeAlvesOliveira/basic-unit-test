package org.unit.test.service;

import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.unit.test.entity.Car;
import org.unit.test.entity.CarDTO;
import org.unit.test.entity.GermanCarBrand;
import org.unit.test.exception.WrongBrandException;
import org.unit.test.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

public class CarServiceTest {
    @InjectMocks
    private CarService carService;
    @Mock
    private CarRepository carRepository;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.initMocks(this);
        Car golf = new Car();
        golf.setId(1l);
        golf.setModel("golf");
        golf.setBrand(GermanCarBrand.VOLKSWAGEN.name());

        Car passat = new Car();
        passat.setId(2l);
        passat.setModel("passat");
        passat.setBrand(GermanCarBrand.VOLKSWAGEN.name());

        List<Car> cars = new ArrayList<Car>();
        cars.add(golf);
        cars.add(passat);

        Mockito.when(carRepository.findByIdOptional(1l)).thenReturn(Optional.of(golf));
        Mockito.when(carRepository.listAll()).thenReturn(cars);
    }

    @Test
    public void getByIdTest() {
        CarDTO carReturned = carService.getById(1l);
        Assertions.assertEquals("golf", carReturned.getModel());
    }

    @Test
    public void getByIdNotFoundTest() {
        Assertions.assertThrows(NotFoundException.class, () -> carService.getById(2l));
    }

    @Test
    public void getAllTest() {
        List<CarDTO> cars = carService.getAll();
        Assertions.assertEquals(2, cars.size());
    }

    @Test
    public void updateCarTest() {
        CarDTO carDTO = new CarDTO();
        carDTO.setModel("golf variant");
        carDTO.setBrand(GermanCarBrand.VOLKSWAGEN.name());
        try {
            carService.updateCar(1l, carDTO);

            Mockito.verify(carRepository, Mockito.times(1)).findByIdOptional(1l);
            Mockito.verify(carRepository, Mockito.times(1)).persist(any(Car.class));
        } catch (WrongBrandException e) {
            Assertions.fail("WrongBrandException should not be thrown");
        }
    }

    @Test
    public void updateCarErrorNotFoundTest() {
        CarDTO carDTO = new CarDTO();
        carDTO.setModel("golf variant");
        carDTO.setBrand(GermanCarBrand.VOLKSWAGEN.name());

        Assertions.assertThrows(NotFoundException.class, () -> carService.updateCar(3l, carDTO));
    }

    @Test
    public void updateCarErrorWrongBrandTest() {
        CarDTO carDTO = new CarDTO();
        carDTO.setModel("golf variant");
        carDTO.setBrand("Toyota");
        Assertions.assertThrows(WrongBrandException.class, () -> carService.updateCar(1l, carDTO));
    }

    @Test
    public void createCarTest() {
        CarDTO carDTO = new CarDTO();
        carDTO.setModel("golf variant");
        carDTO.setBrand(GermanCarBrand.VOLKSWAGEN.name());
        try {
            carService.createCar(carDTO);
            Mockito.verify(carRepository, Mockito.times(1)).persist(any(Car.class));
        } catch (WrongBrandException e) {
            Assertions.fail("WrongBrandException should not be thrown");
        }
    }

    @Test
    public void createCarWrongBrandTest() {
        CarDTO carDTO = new CarDTO();
        carDTO.setModel("golf variant");
        carDTO.setBrand("Toyota");

        Assertions.assertThrows(WrongBrandException.class, () -> carService.createCar(carDTO));
    }

    @Test
    public void deleteCarTest() {
        carService.delete(1l);
        Mockito.verify(carRepository, Mockito.times(1)).deleteById(1l);
    }
}
