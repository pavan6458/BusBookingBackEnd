package BusBooking.BusBooking.Service.serviceImpp;

import BusBooking.BusBooking.DTO.Request.BookingRegReq;
import BusBooking.BusBooking.DTO.Response.BookingRegResp;
import BusBooking.BusBooking.Entity.Booking;
import BusBooking.BusBooking.Entity.Schedule;
import BusBooking.BusBooking.Entity.User;
import BusBooking.BusBooking.Exception.DataNotFounException;
import BusBooking.BusBooking.Repository.BookingRepository;
import BusBooking.BusBooking.Repository.ScheduleRepository;
import BusBooking.BusBooking.Repository.UserRepository;
import BusBooking.BusBooking.Service.BookingService;
import BusBooking.BusBooking.Utils.GenerateId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpp implements BookingService {
    private UserRepository userRepository;
    private ModelMapper mapper;
    private BookingRepository bookingRepository;
    private ScheduleRepository scheduleRepository;
    @Autowired
    public BookingServiceImpp(UserRepository userRepository, ModelMapper mapper, BookingRepository bookingRepository, ScheduleRepository scheduleRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.bookingRepository = bookingRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public BookingRegResp createBooking(BookingRegReq bookingRegReq) {
        Booking booking = new Booking();
        booking.setId(GenerateId.BuildId());
        booking.setStatus(bookingRegReq.getStatus());
        booking.setTotalPassengers(bookingRegReq.getTotalPassengers());
        booking.setTotalAmount(bookingRegReq.getTotalAmount());
        Schedule schedule = scheduleRepository.findById(bookingRegReq.getScheduleId()).orElseThrow(() ->
                new DataNotFounException("Schedule Not found with id " + bookingRegReq.getScheduleId()));
        User user = userRepository.findById(bookingRegReq.getUserid()).orElseThrow(() ->
                new DataNotFounException("user not found with id" + bookingRegReq.getUserid()));
        booking.setSchedule(schedule);
        booking.setUser(user);
        Booking save = bookingRepository.save(booking);
        return convertBookingToBookingRegResp(save);
    }


    @Override
    public List<BookingRegResp> getAllBookings(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFounException("User not found with id " + userId));
        List<Booking> booking = user.getBooking();
        if(booking.size()>0)
        {
            List<BookingRegResp> collect = booking.stream().map((list) -> convertBookingToBookingRegResp(list)).collect(Collectors.toList());
            return collect;
        }
        else {
            throw new DataNotFounException("No Booking found with id "+ userId);
        }

    }

    @Override
    public BookingRegResp getBookingById(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new DataNotFounException("boooking not found with id " + bookingId));
        return convertBookingToBookingRegResp(booking);
    }

    @Override
    public BookingRegResp cancaelBookingById(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new DataNotFounException("boooking not found with id " + bookingId));
        bookingRepository.delete(booking);
        Optional<Booking> deleteeduser = bookingRepository.findById(bookingId);
        if(deleteeduser.isEmpty())
        {
            return convertBookingToBookingRegResp(booking);
        }
       return null;
    }

    public BookingRegResp convertBookingToBookingRegResp(Booking booking)
    {
        return mapper.map(booking,BookingRegResp.class);
    }
}
