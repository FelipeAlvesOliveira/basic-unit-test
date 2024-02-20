package org.unit.test.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestPath;
import org.unit.test.entity.CarDTO;
import org.unit.test.exception.WrongBrandException;
import org.unit.test.service.CarService;

import java.net.URI;
import java.util.List;

@Path("/cars")
public class CarController {
    @Inject
    CarService carService;

    @GET
    public List<CarDTO> getAllCars() {
        return carService.getAll();
    }
    @GET
    @Path("/{id}")
    public CarDTO getCarById(@RestPath Long id) {
        return carService.getById(id);
    }

    @PUT
    @Path("/{id}")
    public void updateCar(@RestPath Long id, CarDTO car) {
        try {
            carService.updateCar(id, car);
        } catch (WrongBrandException e) {
            // TODO FELIPE: CHECK THIS EXCEPTION
            throw new RuntimeException(e);
        }
    }

    @POST
    @Consumes("application/json")
    public Response createCar(CarDTO car) throws WrongBrandException {
        Long id = carService.createCar(car);
        return Response.created(URI.create("http://localhost:8080/cars/"+id)).build();
    }

    @DELETE
    @Path("/{id}")
    public void deleteCar(@RestPath Long id) {
        carService.delete(id);
    }
}
