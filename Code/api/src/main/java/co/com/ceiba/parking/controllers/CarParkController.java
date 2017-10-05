package co.com.ceiba.parking.controllers;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.parking.domain.CarPark;
import co.com.ceiba.parking.services.CarParkService;

@RestController
@RequestMapping("/vehicles")
public class CarParkController {

  @Autowired
  private CarParkService carParkService;

  @PostMapping(path = "/park")
  public ResponseEntity<CarPark> parkVehicle(@Valid @RequestBody CarPark carPark) {

    carPark.setEntryDate(LocalDateTime.now());
    CarPark carParkRe = carParkService.park(carPark);
    return new ResponseEntity<>(carParkRe, HttpStatus.CREATED);
  }

}
