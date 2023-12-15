package BusBooking.BusBooking.Repository;

import BusBooking.BusBooking.Entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus ,Integer> {
}
