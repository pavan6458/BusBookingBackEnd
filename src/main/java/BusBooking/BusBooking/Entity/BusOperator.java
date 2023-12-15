package BusBooking.BusBooking.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BusOperator {
    @Id
    private Integer id;
    private String operatorName;
    private String operatorEmail;
    private String OperatorMobile;
    @CreationTimestamp
    private Date createdDate;
    @UpdateTimestamp
    private Date UpdatedDate;
    @OneToMany(mappedBy = "busOperator", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Bus> buses =new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "admin_id")
    private BusCompanyAdmin busAdmin;


}
