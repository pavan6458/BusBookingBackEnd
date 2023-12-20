package BusBooking.BusBooking.Service;

import BusBooking.BusBooking.DTOs.PassangerDto;
import BusBooking.BusBooking.DTOs.PassangerList;

import java.util.List;

public interface PassangerService {
    public PassangerDto addPassangersToBooking (PassangerDto passangerDto);

    public List<PassangerList> GetAllSchedulePassangersList(Integer scheduleId);



}
