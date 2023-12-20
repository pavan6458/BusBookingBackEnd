package BusBooking.BusBooking.Service.serviceImpp;

import BusBooking.BusBooking.DTOs.PassangerDto;
import BusBooking.BusBooking.DTOs.PassangerList;
import BusBooking.BusBooking.Entity.Booking;
import BusBooking.BusBooking.Entity.Passenger;
import BusBooking.BusBooking.Entity.Schedule;
import BusBooking.BusBooking.Exception.DataNotFounException;
import BusBooking.BusBooking.Repository.BookingRepository;
import BusBooking.BusBooking.Repository.PassengerRepository;
import BusBooking.BusBooking.Repository.ScheduleRepository;
import BusBooking.BusBooking.Service.PassangerService;
import BusBooking.BusBooking.Service.ScheduleService;
import BusBooking.BusBooking.Utils.GenerateId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassangersServiceImpp implements PassangerService {
    private PassengerRepository passengerRepository;
    private ScheduleRepository scheduleRepository;
    private BookingRepository bookingRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PassangersServiceImpp(PassengerRepository passengerRepository, ScheduleRepository scheduleRepository, BookingRepository bookingRepository, ModelMapper modelMapper) {
        this.passengerRepository = passengerRepository;
        this.scheduleRepository = scheduleRepository;
        this.bookingRepository = bookingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PassangerDto addPassangersToBooking(PassangerDto passangerDto) {
        List<PassangerDto> passangerDtos = new ArrayList<>();
        Schedule schedule = scheduleRepository.findById(passangerDto.getScheduleId()).orElseThrow(() ->
                new DataNotFounException("Schedule not found with id" + passangerDto.getScheduleId()));

        Booking booking = bookingRepository.findById(passangerDto.getBookingId()).orElseThrow(() ->
                new DataNotFounException("Booking not found with id " + passangerDto.getBookingId()));

        List<PassangerList> collect = passangerDto.getPassangerLists().stream().map((list) ->
        {
            Passenger passenger = convertPassangerDtoTOPassanget(list);
            passenger.setId(GenerateId.BuildId());
            passenger.setSchedule(schedule);
            passenger.setBooking(booking);


            Passenger objectSaved = passengerRepository.save(passenger);
            return convertPAssangetTopassangerDTo(objectSaved);
        }).collect(Collectors.toList());

        PassangerDto passangerDto1 = new PassangerDto();
        passangerDto1.setPassangerLists(collect);
        passangerDto1.setBookingId(passangerDto.getScheduleId());
        passangerDto1.setScheduleId(passangerDto.getScheduleId());
        return passangerDto1;
    }

    @Override
    public List<PassangerList> GetAllSchedulePassangersList(Integer scheduleId) {
        List<Passenger> PassengerList = passengerRepository.findByScheduleId(scheduleId);
        return PassengerList.stream().map((list)-> convertPAssangetTopassangerDTo(list)).collect(Collectors.toList());


    }

    public Passenger convertPassangerDtoTOPassanget(PassangerList passangerList)
    {
        return  modelMapper.map(passangerList,Passenger.class);

    }

    public PassangerList convertPAssangetTopassangerDTo(Passenger passenger)
    {
        return  modelMapper.map(passenger,PassangerList.class);

    }
}
