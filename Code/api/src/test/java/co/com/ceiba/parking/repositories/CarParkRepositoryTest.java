package co.com.ceiba.parking.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parking.builders.DbCarParkBuilder;
import co.com.ceiba.parking.entities.DbCarPark;

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
    // Arrange
    DbCarPark entity = new DbCarParkBuilder().withCar("RMS34D").build();
    entityManager.persist(entity);

    // Act
    Optional<DbCarPark> foundCarPark = carParkRepository.findByVehiclePlateAndExitDateIsNull(entity.getVehicle().getPlate());

    // Assert
    assertThat(foundCarPark).isPresent();
  }

  @Test
  public void whenCountParkCars_thenReturnNumberCars() {
    // Arrange
    DbCarPark entity = new DbCarParkBuilder().withCar("RMS34D").build();
    entityManager.persist(entity);

    // Act
    int count = carParkRepository.countByVehicleTypeAndExitDateIsNull(entity.getVehicle().getType());

    // Assert
    assertThat(count).isEqualTo(1);
  }

  @Test
  public void whenCountParkMotorcycle_thenReturnNumberCars() {
    // Arrange
    DbCarPark entity = new DbCarParkBuilder().withMotorcycle("RMS34E").build();
    entityManager.persist(entity);

    // Act
    int count = carParkRepository.countByVehicleTypeAndExitDateIsNull(entity.getVehicle().getType());

    // Assert
    assertThat(count).isEqualTo(1);
  }

  @Test
  public void whenFindByExitDateIsNull_thenReturnParkedVehicles() {
    // Arrange
    String plate = "RMS34D";
    DbCarPark entity = new DbCarParkBuilder().withCar(plate).build();
    entity = entityManager.persist(entity);

    // Act
    List<DbCarPark> parkedVehicles = carParkRepository.findByExitDateIsNull();

    // Assert
    assertThat(parkedVehicles).contains(entity);
  }

  @Test
  public void whenFindByExitDateIsNull_thenReturnNothingParkedVehicles() {
    // Arrange
    String plate = "RMS34D";
    DbCarPark entity = new DbCarParkBuilder().withCar(plate).withExitDate(LocalDateTime.now()).build();
    entity = entityManager.persist(entity);

    // Act
    List<DbCarPark> parkedVehicles = carParkRepository.findByExitDateIsNull();

    // Assert
    assertThat(parkedVehicles).doesNotContain(entity);
  }

}
