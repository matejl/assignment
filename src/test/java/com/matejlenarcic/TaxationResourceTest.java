package com.matejlenarcic;

import com.matejlenarcic.rest.trader.dto.TraderRequest;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
class TaxationResourceTest {
    @Test
    void testGeneralPerRate() {
        Integer traderId = given()
            .body(new TraderRequest("PER_RATE", new BigDecimal("0.1"), null))
            .contentType("application/json")
            .when()
            .post("/traders")
            .then().statusCode(201)
            .extract().path("id");

        given()
            .when().get("/taxations/general?traderId=%s&playedAmount=%s&odd=%s".formatted(traderId, 10, 2))
            .then()
            .statusCode(200)
            .body("possibleReturnAmount", equalTo(18.0f))
            .body("possibleReturnAmountBeforeTax", equalTo(20))
            .body("possibleReturnAmountAfterTax", equalTo(18.0f))
            .body("taxRate", equalTo(0.10f))
            .body("taxAmount", equalTo(0));
    }

    @Test
    void testGeneralPerAmount() {
        Integer traderId = given()
            .body(new TraderRequest("PER_AMOUNT", null, new BigDecimal("2")))
            .contentType("application/json")
            .when()
            .post("/traders")
            .then().statusCode(201)
            .extract().path("id");

        given()
            .when().get("/taxations/general?traderId=%s&playedAmount=%s&odd=%s".formatted(traderId, 20, 2))
            .then()
            .statusCode(200)
            .body("possibleReturnAmount", equalTo(38.0f))
            .body("possibleReturnAmountBeforeTax", equalTo(40))
            .body("possibleReturnAmountAfterTax", equalTo(38.0f))
            .body("taxRate", equalTo(0))
            .body("taxAmount", equalTo(2f));
    }

    @Test
    void testWinningsPerRate() {
        Integer traderId = given()
            .body(new TraderRequest("PER_RATE", new BigDecimal("0.15"), null))
            .contentType("application/json")
            .when()
            .post("/traders")
            .then().statusCode(201)
            .extract().path("id");

        given()
            .when().get("/taxations/winnings?traderId=%s&playedAmount=%s&odd=%s".formatted(traderId, 10, 3.2))
            .then()
            .statusCode(200)
            .body("possibleReturnAmount", equalTo(18.7f))
            .body("possibleReturnAmountBeforeTax", equalTo(22f))
            .body("possibleReturnAmountAfterTax", equalTo(18.7f))
            .body("taxRate", equalTo(0.15f))
            .body("taxAmount", equalTo(0));
    }

    @Test
    void testWinningsPerAmount() {
        Integer traderId = given()
            .body(new TraderRequest("PER_AMOUNT", null, new BigDecimal("2.3")))
            .contentType("application/json")
            .when()
            .post("/traders")
            .then().statusCode(201)
            .extract().path("id");

        given()
            .when().get("/taxations/winnings?traderId=%s&playedAmount=%s&odd=%s".formatted(traderId, 20, 2))
            .then()
            .statusCode(200)
            .body("possibleReturnAmount", equalTo(17.7f))
            .body("possibleReturnAmountBeforeTax", equalTo(20))
            .body("possibleReturnAmountAfterTax", equalTo(17.7f))
            .body("taxRate", equalTo(0))
            .body("taxAmount", equalTo(2.3f));
    }
}