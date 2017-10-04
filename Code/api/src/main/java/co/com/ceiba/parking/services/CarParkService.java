package co.com.ceiba.parking.services;

import co.com.ceiba.parking.domain.CarPark;

public interface CarParkService {

  CarPark park(CarPark carpark);

  boolean unPark();

}
