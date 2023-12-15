package BusBooking.BusBooking.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

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

}
