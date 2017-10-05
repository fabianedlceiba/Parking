package co.com.ceiba.parking.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
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
  public void t() throws IOException, Exception {
    // Arrange
    CarPark carPark = new CarParkBuilder().withCar("RDF23D").build();

    // Act

    //@formatter:off
    mvc.perform(post("/vehicles/park").contentType(MediaType.APPLICATION_JSON)
                                      .content(JsonHelper.toJson(carPark)))
       .andExpect(status().isCreated());

    
    //@formatter:on
    Optional<DbCarPark> carParkSaved = repository.findByVehiclePlateAndExitDateIsNull(carPark.getVehicle().getPlate());

    assertThat(carParkSaved).contains(carPark.toEntity());

  }

}
