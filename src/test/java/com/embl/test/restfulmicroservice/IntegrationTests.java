package com.embl.test.restfulmicroservice;
import static io.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.embl.test.restfulmicroservice.config.model.AuthenticationRequest;
import com.embl.test.restfulmicroservice.config.model.PersonV1DTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Slf4j
public class IntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    ObjectMapper objectMapper;

    private String token;

    @Before
    public void setup() {
        RestAssured.port = this.port;
        token = given()
            .contentType(ContentType.JSON)
            .body(AuthenticationRequest.builder().username("user").password("password").build())
            .when().post("/auth/token")
            .andReturn().jsonPath().getString("token");
        log.debug("Got token:" + token);
    }

    @Test
    public void getAllPerson() throws Exception {
        //@formatter:off
         given()

            .accept(ContentType.JSON)

        .when()
            .get("/v1/persons")

        .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK);
         //@formatter:on
    }

    @Test
    public void testSave() throws Exception {
        //@formatter:off
        given()

            .contentType(ContentType.JSON)
            .body(PersonV1DTO.builder()
            		.firstName("test")
            		.lastName("lastname")
            		.age(50)
            		.favouriteColour("red")
            		.build())

        .when()
            .post("/v1/persons")

        .then()
            .statusCode(403);

        //@formatter:on
    }

    @Test
    public void testSaveWithAuth() throws Exception {

        //@formatter:off
        given()
                .header("Authorization", "Bearer "+token)
                .contentType(ContentType.JSON)
                .body(PersonV1DTO.builder()
                		.firstName("test")
                		.lastName("lastname")
                		.age(50)
                		.favouriteColour("red")
                		.build())

                .when()
                .post("/v1/persons")

                .then()
                .statusCode(201);

        //@formatter:on
    }

    @Test
    @Ignore
    public void testSaveWithInvalidAuth() throws Exception {

        //@formatter:off
        given()
                .header("Authorization", "Bearer "+"invalidtoken")
                .contentType(ContentType.JSON)
                .body(PersonV1DTO.builder()
                		.firstName("test")
                		.lastName("lastname")
                		.age(50)
                		.favouriteColour("red")
                		.build())

                .when()
                .post("/v1/vehicles")

                .then()
                .statusCode(403);

        //@formatter:on
    }

}