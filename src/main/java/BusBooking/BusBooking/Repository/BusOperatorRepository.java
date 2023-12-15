package BusBooking.BusBooking.Repository;

import BusBooking.BusBooking.Entity.BusOperator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusOperatorRepository extends JpaRepository<BusOperator,Integer> {
}
