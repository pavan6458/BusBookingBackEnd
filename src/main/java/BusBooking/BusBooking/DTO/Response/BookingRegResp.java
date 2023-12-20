package BusBooking.BusBooking.DTO.Response;

import BusBooking.BusBooking.DTOs.ScheduleDTo;
import BusBooking.BusBooking.Entity.Schedule;
import BusBooking.BusBooking.Entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class BookingRegResp {
    private Integer id;
    private Integer totalPassengers;
    private Double totalAmount;
    private String status;
    private ScheduleDTo schedule;
    private BusRegResp userId;
}
