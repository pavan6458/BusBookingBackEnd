package BusBooking.BusBooking.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "bookings")
@Setter
@Getter
@NoArgsConstructor
public class Booking implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "total_passengers")
    private Integer totalPassengers;
    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "status")
    private String status;
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    private Schedule schedule;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Passenger> passengers;
}
