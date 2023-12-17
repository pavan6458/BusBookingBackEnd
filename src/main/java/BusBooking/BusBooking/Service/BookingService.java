package BusBooking.BusBooking.Service;

import BusBooking.BusBooking.DTO.Request.BookingRegReq;
import BusBooking.BusBooking.DTO.Response.BookingRegResp;

import java.util.List;

public interface BookingService {

    public BookingRegResp createBooking (BookingRegReq bookingRegReq);

    public List<BookingRegResp> getAllBookings(Integer userId);
    public BookingRegResp getBookingById(Integer id);
    public BookingRegResp cancaelBookingById(Integer id);
}
