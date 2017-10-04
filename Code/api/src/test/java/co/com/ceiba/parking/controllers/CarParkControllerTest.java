package co.com.ceiba.parking.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import co.com.ceiba.parking.services.CarParkService;

@RunWith(SpringRunner.class)
@WebMvcTest(CarParkController.class)
public class CarParkControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private CarParkService service;

  @Test
  public void given_whenVehiclePark_thenReturnJsonArray() {

  }

}
