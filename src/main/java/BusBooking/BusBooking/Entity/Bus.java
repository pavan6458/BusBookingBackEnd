package BusBooking.BusBooking.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Set;
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bus {
    @Id
    private Integer id;
    private String busName;
    private Integer totalSeats;
    private String busType;
    @ManyToOne
    @JoinColumn(name = "operator_id", referencedColumnName = "id")
    private BusOperator busOperator;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private BusCompanyAdmin busCompanyAdmin;

}
