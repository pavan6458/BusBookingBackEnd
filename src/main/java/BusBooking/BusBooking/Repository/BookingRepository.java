package BusBooking.BusBooking.Repository;

import BusBooking.BusBooking.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
}
