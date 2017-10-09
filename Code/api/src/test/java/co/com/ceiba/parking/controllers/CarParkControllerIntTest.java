package co.com.ceiba.parking.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import co.com.ceiba.parking.ApiApplication;
import co.com.ceiba.parking.builders.CarParkBuilder;
import co.com.ceiba.parking.builders.DbCarParkBuilder;
import co.com.ceiba.parking.domain.CarPark;
import co.com.ceiba.parking.entities.DbCarPark;
import co.com.ceiba.parking.helpers.JsonHelper;
import co.com.ceiba.parking.repositories.CarParkRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ApiApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CarParkControllerIntTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private CarParkRepository repository;

  @Test
  public void givenNewCarPark_whenCarPark_thenStatusCreated() throws IOException, Exception {
    // Arrange
    CarPark carPark = new CarParkBuilder().withCar("RDF23D").build();

    // Act

    //@formatter:off
    mvc.perform(post("/vehicles/park").contentType(MediaType.APPLICATION_JSON)
                                      .content(JsonHelper.toJson(carPark)))
       .andExpect(status().isCreated());

    
    //@formatter:on
    Optional<DbCarPark> carParkSaved = repository.findByVehiclePlateAndExitDateIsNull(carPark.getVehicle().getPlate());

    // Assert
    assertThat(carParkSaved).isPresent();

  }

  @Test
  public void givenAmountToBePaid_whenUnparkVehicle_thenStatusOk() throws IOException, Exception {
    // Arrange
    String plate = "RSX349";
    LocalDateTime entryDate = LocalDateTime.of(2017, Month.OCTOBER, 5, 8, 0);

    DbCarPark carPark = new DbCarParkBuilder().withCar(plate).withEntryDate(entryDate).build();

    repository.save(carPark);

    // Act
    // @formatter:off
    mvc.perform(put("/vehicles/" + plate + "/park").contentType(MediaType.APPLICATION_JSON))
       .andExpect(status().isOk());
    //@formatter:on

    //@formatter:on
    Optional<DbCarPark> carParkSaved = repository.findByVehiclePlateAndExitDateIsNull(plate);

    // Assert
    assertThat(carParkSaved).isNotPresent();
  }

  @Test
  public void givenParkedVehicles_whenGetParkedVehicles_thenStatusOk() throws IOException, Exception {
    // Arrange
    String plate = "RSX348";
    DbCarPark carPark = new DbCarParkBuilder().withCar(plate).withEntryDate(LocalDateTime.now()).build();
    repository.save(carPark);

    // Act
    // @formatter:off
    mvc.perform(get("/vehicles/park").contentType(MediaType.APPLICATION_JSON))
       .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
       .andExpect(status().isOk());
    //@formatter:on

  }

}
