package co.com.ceiba.parking.services.impl;

import static co.com.ceiba.parking.enums.EVehicleType.CAR;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parking.domain.CarPark;
import co.com.ceiba.parking.exceptions.LimitExceededException;
import co.com.ceiba.parking.exceptions.NoAvailableDayException;
import co.com.ceiba.parking.repositories.CarParkRepository;
import co.com.ceiba.parking.services.CarParkService;

@RunWith(SpringRunner.class)
public class CarParkServiceImplTest {

  @TestConfiguration
  static class CarParkServiceImplTestContextConfiguration {

    @Bean
    public CarParkServiceImpl carParkService() {
      return new CarParkServiceImpl();
    }
  }

  @Autowired
  private CarParkService carParkService;

  @MockBean
  private CarParkRepository carParkRepository;

  @Before
  public void setUp() {
    when(carParkRepository.countByVehicleTypeAndExitDateIsNull(CAR)).thenReturn(21);
  }

  @Test(expected = NoAvailableDayException.class)
  public void whenPlateStartWithA_thenThrowNoAvailableDayException() {
    carParkService.park(new CarPark("ARD23D", CAR));
  }

  @Test(expected = LimitExceededException.class)
  public void whenThereAre21Cars_thenThrowLimitExceededException() {
    carParkService.park(new CarPark("CRD23D", CAR));
  }

}
