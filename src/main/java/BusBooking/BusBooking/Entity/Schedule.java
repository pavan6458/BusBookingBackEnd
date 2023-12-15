package BusBooking.BusBooking.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Schedule implements Serializable {
    @Id
    private Integer id;
    private Date departureTime;
    private String origin;
    private String destination;
    private String duration;
    private Integer distance;
    @Column(name = "arrival_time")
    private Date arrivalTime;
    @Column(name = "price")
    private Double price;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Booking> bookings;


    @ManyToOne
    @JoinColumn(name = "bus_id", referencedColumnName = "id")
    @JsonIgnore
    private Bus bus;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "adminId")
    private BusCompanyAdmin busCompanyAdmin;
}
