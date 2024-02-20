package org.unit.test.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.unit.test.entity.Car;
import org.unit.test.entity.CarDTO;
import org.unit.test.entity.GermanCarBrand;
import org.unit.test.exception.WrongBrandException;
import org.unit.test.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

// Entidade será criada a cada requisição e depois eliminada
@RequestScoped
@Transactional
public class CarService {

    @Inject
    CarRepository carRepository;

    public CarDTO getById(Long id) {
        Car car = carRepository.findById(id);
        return Car.toDTO(car);
    }

    public List<CarDTO> getAll() {
        List<CarDTO> result = new ArrayList<>();
        List<Car> cars = carRepository.listAll();
        if (cars != null && !cars.isEmpty()) {
            result = cars.stream()
                    .map(Car::toDTO)
                    .toList();
        }
        return result;
    }

    public void updateCar(Long id, CarDTO carDTO) throws WrongBrandException {
        // check is german brand
        checkBrandIsGerman(carDTO.getBrand());

        Car car = carRepository.findById(id);
        if (car == null) {
            throw new NotFoundException("Car with id "+id+" not found");
        }

        Car newCar = Car.fromDTO(carDTO);
        newCar.setId(car.getId());

        carRepository.persist(newCar);
    }

    public Long createCar(CarDTO carDTO) throws WrongBrandException {
        // check is german brand
        checkBrandIsGerman(carDTO.getBrand());

        Car newCar = Car.fromDTO(carDTO);

        carRepository.persist(newCar);
        return newCar.getId();
    }

    public void delete(Long id) {
        carRepository.deleteById(id);
    }

    private void checkBrandIsGerman(String brand) throws WrongBrandException {
        try {
            if (brand == null || brand.isBlank()) {
                throw new WrongBrandException("Should to provide a german brand");
            }
            GermanCarBrand.valueOf(brand);
        } catch (IllegalArgumentException ex) {
            throw new WrongBrandException(brand + " is not a german brand", ex);
        }
    }
}
