package co.com.ceiba.parking.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parking.entities.DbCarPark;
import co.com.ceiba.parking.enums.EVehicleType;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CarParkRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private CarParkRepository carParkRepository;

  @Test
  public void whenFindByVehiclePlate_thenReturnCarPark() {

    final String plate = "RMS34D";

    entityManager.persist(new DbCarPark(plate, EVehicleType.CAR, LocalDateTime.now(), (short) 1));

    Optional<DbCarPark> foundCarPark = carParkRepository.findByVehiclePlate(plate);

    foundCarPark.ifPresent(car -> assertThat(car.getVehicle().getPlate()).isEqualTo(plate));

  }

  @Test
  public void whenCountParkCars_thenReturnNumberCars() {

    final String plate = "RMS34D";
    final EVehicleType carType = EVehicleType.CAR;

    entityManager.persist(new DbCarPark(plate, carType, LocalDateTime.now(), (short) 1));

    int count = carParkRepository.countByVehicleTypeAndExitDateIsNull(carType);

    assertTrue(count == 1);
  }

  @Test
  public void whenCountParkMotorcycle_thenReturnNumberCars() {

    final String plate = "RMS34C";
    final EVehicleType carType = EVehicleType.MOTORCYCLE;

    entityManager.persist(new DbCarPark(plate, carType, LocalDateTime.now(), (short) 1));

    int count = carParkRepository.countByVehicleTypeAndExitDateIsNull(carType);

    assertTrue(count == 1);
  }

}
