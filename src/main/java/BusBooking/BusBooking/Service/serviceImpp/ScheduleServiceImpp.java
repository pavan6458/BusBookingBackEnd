package BusBooking.BusBooking.Service.serviceImpp;

import BusBooking.BusBooking.DTO.Request.BusSearchReqDto;
import BusBooking.BusBooking.DTO.Request.ScheduleRegReq;
import BusBooking.BusBooking.DTO.Response.ScheduleRegResp;
import BusBooking.BusBooking.Entity.Bus;
import BusBooking.BusBooking.Entity.BusCompanyAdmin;
import BusBooking.BusBooking.Entity.Schedule;
import BusBooking.BusBooking.Exception.DataNotFounException;
import BusBooking.BusBooking.Repository.BusCompanyAdminRepository;
import BusBooking.BusBooking.Repository.BusRepository;
import BusBooking.BusBooking.Repository.ScheduleRepository;
import BusBooking.BusBooking.Service.ScheduleService;
import BusBooking.BusBooking.Utils.GenerateId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpp implements ScheduleService {
    private ModelMapper mapper;
    private ScheduleRepository scheduleRepository;
    private BusCompanyAdminRepository busCompanyAdminRepository;
    private BusRepository busRepository;

    public ScheduleServiceImpp(ModelMapper mapper, ScheduleRepository scheduleRepository, BusCompanyAdminRepository busCompanyAdminRepository, BusRepository busRepository) {
        this.mapper = mapper;
        this.scheduleRepository = scheduleRepository;
        this.busCompanyAdminRepository = busCompanyAdminRepository;
        this.busRepository = busRepository;
    }

    @Override
    public ScheduleRegResp createSchedule(ScheduleRegReq scheduleRegReq) {
        Schedule schedule = new Schedule();
        schedule.setId(GenerateId.BuildId());
        schedule.setDepartureTime(scheduleRegReq.getDepartureTime());
        schedule.setArrivalTime(scheduleRegReq.getArrivalTime());
        schedule.setPrice(scheduleRegReq.getPrice());
        schedule.setDuration(scheduleRegReq.getDuration());
        schedule.setOrigin(scheduleRegReq.getOrigin());
        schedule.setDestination(scheduleRegReq.getDestination());
        schedule.setDistance(scheduleRegReq.getDistance());
        BusCompanyAdmin admin = busCompanyAdminRepository.findById(scheduleRegReq.getAdminId()).orElseThrow(() -> new DataNotFounException("Admin not found with id " + scheduleRegReq.getAdminId()));
        Bus bus = busRepository.findById(scheduleRegReq.getBusId()).orElseThrow(() -> new DataNotFounException("Admin not found with id " + scheduleRegReq.getBusId()));
        schedule.setBus(bus);
        schedule.setBusCompanyAdmin(admin);
        Schedule Scheduled = scheduleRepository.save(schedule);

        return convertScheduleToScheduleRegResp(Scheduled);
    }

    @Override
    public ScheduleRegResp updatedSchedule(Integer scheduleId, ScheduleRegReq scheduleRegReq) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new DataNotFounException("Schedule not  found with id " + scheduleId));
        schedule.setDepartureTime(scheduleRegReq.getDepartureTime());
        schedule.setArrivalTime(scheduleRegReq.getArrivalTime());
        schedule.setPrice(scheduleRegReq.getPrice());
        schedule.setDuration(scheduleRegReq.getDuration());
        schedule.setOrigin(scheduleRegReq.getDuration());
        schedule.setDestination(scheduleRegReq.getDestination());
        schedule.setDistance(scheduleRegReq.getDistance());
        Bus bus = busRepository.findById(scheduleRegReq.getBusId()).orElseThrow(() -> new DataNotFounException("bus not found with id " + scheduleRegReq.getBusId()));
        schedule.setBus(bus);
        Schedule save = scheduleRepository.save(schedule);
        return convertScheduleToScheduleRegResp(save);
    }

    @Override
    public Set<ScheduleRegResp> getAllSchedule(Integer adminId) {
        BusCompanyAdmin busCompanyAdmin = busCompanyAdminRepository.findById(adminId).orElseThrow(() -> new DataNotFounException("Admin not found with id " + adminId));
        Set<ScheduleRegResp> collect = busCompanyAdmin.getSchedules().stream().map(this::convertScheduleToScheduleRegResp).collect(Collectors.toSet());
        return collect;
    }

    @Override
    public ScheduleRegResp getSchudleById(Integer sheduleId) {
        Schedule schedule = scheduleRepository.findById(sheduleId)
                .orElseThrow(() -> new DataNotFounException("Schedule not found with id" + sheduleId));
        return convertScheduleToScheduleRegResp(schedule);
    }

    @Override
    public ScheduleRegResp deleteSchudule(Integer sheduleId) {
        Schedule schedule = scheduleRepository.findById(sheduleId)
                .orElseThrow(() -> new DataNotFounException("Schedule not found with id" + sheduleId));
        scheduleRepository.delete(schedule);
        Optional<Schedule> deletedUser = scheduleRepository.findById(sheduleId);
        if(deletedUser.isEmpty())
        {
            return convertScheduleToScheduleRegResp(schedule);
        }
        return null;
    }

    @Override
    public Set<ScheduleRegResp> searchBus(BusSearchReqDto busSearchReqDto) {
        List<Schedule> searchedBuses = scheduleRepository.findByOriginAndDestinationAndArrivalDate(busSearchReqDto.getOrigin(), busSearchReqDto.getDestination(), busSearchReqDto.getArrivalTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        Set<ScheduleRegResp> collect = searchedBuses.stream().map((items) -> convertScheduleToScheduleRegResp(items)).collect(Collectors.toSet());
        return collect;
    }

    public ScheduleRegResp convertScheduleToScheduleRegResp(Schedule schedule){
        return mapper.map(schedule,ScheduleRegResp.class);
    }
}
