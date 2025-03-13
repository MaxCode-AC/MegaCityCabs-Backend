package com.megacity.cab.service;

import com.megacity.cab.dto.CarDTO;
import com.megacity.cab.exception.CarNotFoundException;
import com.megacity.cab.model.Car;
import com.megacity.cab.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public CarDTO getCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found with ID: " + id));
        return mapToDTO(car);
    }

    public CarDTO addCar(CarDTO carDTO) {
        Car car = mapToEntity(carDTO);
        return mapToDTO(carRepository.save(car));
    }

    public CarDTO updateCar(Long id, CarDTO carDTO) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found with ID: " + id));

        existingCar.setModel(carDTO.getModel());
        existingCar.setNumber(carDTO.getNumber());
        existingCar.setDriverName(carDTO.getDriverName());
        existingCar.setPrice(carDTO.getPrice());

        return mapToDTO(carRepository.save(existingCar));
    }

    public void deleteCar(Long id) {
        if (!carRepository.existsById(id)) {
            throw new CarNotFoundException("Car not found with ID: " + id);
        }
        carRepository.deleteById(id);
    }

    private CarDTO mapToDTO(Car car) {
        CarDTO dto = new CarDTO();
        dto.setId(car.getId());
        dto.setModel(car.getModel());
        dto.setNumber(car.getNumber());
        dto.setDriverName(car.getDriverName());
        dto.setPrice(car.getPrice());
        return dto;
    }

    private Car mapToEntity(CarDTO dto) {
        Car car = new Car();
        car.setModel(dto.getModel());
        car.setNumber(dto.getNumber());
        car.setDriverName(dto.getDriverName());
        car.setPrice(dto.getPrice());
        return car;
    }
}
