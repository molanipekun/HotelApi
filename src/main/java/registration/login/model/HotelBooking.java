package registration.login.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
@Data
@EqualsAndHashCode
@Entity
public class HotelBooking implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "room_number")
    private String roomNumber;
    @Column(name = "book_date")
    private LocalDate bookDate;
    private transient  String formattedDate;
    // Getter and setter
    public String getFormattedDate() {
        return getBookDate().toString();
    }

    public HotelBooking() {

    }
}