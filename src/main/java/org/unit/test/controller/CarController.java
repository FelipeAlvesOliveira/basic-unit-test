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
    public Response getAllCars() {
        return Response
                .ok()
                .entity(carService.getAll())
                .build();
    }
    @GET
    @Path("/{id}")
    public Response getCarById(@RestPath Long id) {
        try {
            CarDTO carDTO = carService.getById(id);
            return Response.ok()
                    .entity(carDTO)
                    .build();
        } catch (NotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateCar(@RestPath Long id, CarDTO car) {
        try {
            carService.updateCar(id, car);
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        } catch (WrongBrandException | NotFoundException e) {
            var status = e instanceof WrongBrandException
                    ? Response.Status.BAD_REQUEST
                    : Response.Status.NOT_FOUND;
            return Response
                    .status(status)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Consumes("application/json")
    public Response createCar(CarDTO car) {
        try {
            Long id = carService.createCar(car);
            return Response.created(URI.create("http://localhost:8080/cars/"+id))
                    .entity(id)
                    .build();
        } catch (WrongBrandException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCar(@RestPath Long id) {
        carService.delete(id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }
}
