package BusBooking.BusBooking.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BusCompanyAdmin {
    @Id
    private Integer id;
    private String companyName;
    private String companyEmail;
    private String CompanyPhone;
    private String passwordHash;
    private String role;
    @CreationTimestamp
    private Date CreatedDate;
    @UpdateTimestamp
    private Date updatedDate;

    @OneToMany(mappedBy = "busAdmin",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<BusOperator> busOperators = new ArrayList<>();
    @OneToMany(mappedBy = "busCompanyAdmin",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Bus> busList = new ArrayList<>();
}
