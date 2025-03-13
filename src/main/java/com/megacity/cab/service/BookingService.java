package com.megacity.cab.service;



import com.megacity.cab.dto.BookingDTO;
import com.megacity.cab.model.Booking;
import com.megacity.cab.model.Car;
import com.megacity.cab.model.Customer;
import com.megacity.cab.repository.BookingRepository;
import com.megacity.cab.repository.CarRepository;
import com.megacity.cab.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    public Booking createBooking(BookingDTO bookingDTO) {
        Optional<Customer> customer = customerRepository.findByNic(bookingDTO.getCustomerNic());
        if (customer.isEmpty()) {
            throw new RuntimeException("Customer not found");
        }

        Optional<Car> car = carRepository.findByModelAndPrice(bookingDTO.getCarModel(), bookingDTO.getPrice());
        if (car.isEmpty()) {
            throw new RuntimeException("Car not found");
        }

        Booking booking = new Booking();
        booking.setCustomer(customer.get());
        booking.setCar(car.get());
        booking.setOrigin(bookingDTO.getOrigin());
        booking.setDestination(bookingDTO.getDestination());
        booking.setDistance(bookingDTO.getDistance());
        booking.setBookingDate(bookingDTO.getBookingDate());

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
