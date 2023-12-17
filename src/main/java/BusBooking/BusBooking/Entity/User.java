package BusBooking.BusBooking.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "user_master")
public class User {
    @Id
    private Integer id;
    @Column(name = "user_name")
    private String name;
    private boolean otp;
    @Column(name = "phone_number",unique = true)
    @Size(min = 10)
    private String mobileNumber;
    public boolean profileCreated;
    @Column(name = "password_hash")
    private String profilePicture;
    private String PasswardHash;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdDate;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedDate;
    private String role;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Booking> booking = new ArrayList<>();

}
