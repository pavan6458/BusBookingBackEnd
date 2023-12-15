package BusBooking.BusBooking.DTO.Response;

import BusBooking.BusBooking.Entity.Bus;
import lombok.Data;

import java.util.Date;

@Data
public class ScheduleRegResp {
    private Integer id;
    private Date arrivalTime;
    private Date departureTime;
    private Double price;
    private String duration;
    private String origin;
    private String destination;
    private Bus bus;

}
