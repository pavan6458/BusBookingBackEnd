package BusBooking.BusBooking.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Schedule {
    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "bus_id", referencedColumnName = "id")
    private Bus bus;
    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private Route route;


    private Date departureTime;

    @Column(name = "arrival_time")
    private Date arrivalTime;
    @Column(name = "price")
    private Double price;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private Set<Booking> bookings;
}
