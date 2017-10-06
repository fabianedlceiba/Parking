package co.com.ceiba.parking.controllers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import co.com.ceiba.parking.builders.CarParkBuilder;
import co.com.ceiba.parking.domain.CarPark;
import co.com.ceiba.parking.helpers.JsonHelper;
import co.com.ceiba.parking.helpers.LocalDateTimeWrapper;
import co.com.ceiba.parking.services.CarParkService;

@RunWith(SpringRunner.class)
@WebMvcTest(CarParkController.class)
public class CarParkControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private CarParkService service;

  @Mock
  private LocalDateTimeWrapper localDateTimeWrapperMock;

  @Test
  public void givenNewCarPark_whenVehiclePark_thenReturnJsonArray() throws Exception {
    // Arrange
    CarPark carPark = new CarParkBuilder().withCar("RDF23D").build();
    given(service.park(any(CarPark.class))).willReturn(carPark);

    // Act

    //@formatter:off
    mvc.perform(post("/vehicles/park").contentType(APPLICATION_JSON)
                                      .content(JsonHelper.toJson(carPark)))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.vehicle.plate").value(carPark.getVehicle().getPlate()))
      .andDo(print());
    
    //@formatter:on

  }

  @Test
  public void givenNewCarPark_whenVehiclePark_thenReturnBadRequest() throws Exception {

    //@formatter:off
    mvc.perform(post("/vehicles/park").contentType(APPLICATION_JSON).content("{}"))
                                      .andExpect(status().isUnprocessableEntity());
    
    //@formatter:on

  }

  @Test
  public void givenAmountToPaid_whenUnparkVehicle_thenReturnOk() throws IOException, Exception {
    // Arrange

    int hours = 2;
    String plate = "RDF23D";
    LocalDateTime entryDate = LocalDateTime.of(2017, Month.OCTOBER, 10, 8, 0);
    LocalDateTime exitDate = entryDate.plusHours(hours);

    CarPark carPark = new CarParkBuilder().withCar(plate).withEntryDate(entryDate).build();

    Long amountToPaid = (long) (2 * carPark.getVehicle().getType().getHour());

    when(localDateTimeWrapperMock.now()).thenReturn(exitDate);
    given(service.unpark(plate, exitDate)).willReturn(amountToPaid);

    // Act

    //@formatter:off
    mvc.perform(put("/vehicles/" + plate + "/park").contentType(APPLICATION_JSON))
      .andExpect(status().isOk())
      .andDo(print());
    
    //@formatter:on

  }

}
