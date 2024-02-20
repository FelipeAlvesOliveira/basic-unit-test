package org.unit.test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusIntegrationTest
@QuarkusTestResource(CarsTestItLifecycleManager.class)
public class CarControllerIT {

    @Test
    @Order(1)
    public void testGetAllCars() {
        given()
                .when().get("/cars")
                .then()
                .statusCode(200)
                .body(is("[]"));
    }

    @Test
    @Order(2)
    public void testCreateCar() {
        given()
                .when()
                .body("{\n" +
                        "\t\"brand\":\"VOLKSWAGEN\",\n" +
                        "    \"releaseDate\":\"2021-01-01\",\n" +
                        "    \"model\":\"Polo\",\n" +
                        "    \"color\":\"Red\",\n" +
                        "    \"kilometers\":\"1234\",\n" +
                        "    \"price\":12000\n" +
                        "}")
                .contentType("application/json")
                .post("/cars")
                .then()
                .statusCode(201)
                .header("Location", "http://localhost:8080/cars/1");
    }


    @Test
    @Order(3)
    public void testGetCar() {
        given()
                .when().get("/cars/1")
                .then()
                .statusCode(200)
                .body(is("{\"brand\":\"VOLKSWAGEN\",\"releaseDate\":\"2021-01-01\",\"model\":\"Polo\",\"color\":\"Red\",\"kilometers\":1234.0,\"price\":12000.0}"));
    }

    @Test
    @Order(4)
    public void testGetCarNotFound() {
        given()
                .when().get("/cars/2")
                .then()
                .statusCode(404);
    }


    @Test
    @Order(5)
    public void testDeleteCar() {
        given()
                .when()
                .delete("/cars/1")
                .then()
                .statusCode(204);
    }

}
