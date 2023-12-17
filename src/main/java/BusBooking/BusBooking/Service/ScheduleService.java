package BusBooking.BusBooking.Service;

import BusBooking.BusBooking.DTO.Request.BusSearchReqDto;
import BusBooking.BusBooking.DTO.Request.ScheduleRegReq;
import BusBooking.BusBooking.DTO.Response.ScheduleRegResp;

import java.util.Set;

public interface ScheduleService {
    public ScheduleRegResp createSchedule(ScheduleRegReq scheduleRegReq);
    public ScheduleRegResp updatedSchedule(Integer sheduleId , ScheduleRegReq scheduleRegReq);
    public Set<ScheduleRegResp> getAllSchedule(Integer adminId);
    public ScheduleRegResp getSchudleById(Integer sheduleId);
    public ScheduleRegResp deleteSchudule(Integer sheduleId);

    public Set<ScheduleRegResp> searchBus(BusSearchReqDto busSearchReqDto);

}
