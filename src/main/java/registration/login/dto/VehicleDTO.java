package registration.login.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data

public class VehicleDTO  {

    private int id;

    private String type;

    private String modelCode;

    private String brandName;

    private LocalDate launchDate;
    private transient  String formattedDate;
    // Getter and setter
    public String getFormattedDate() {
        return getLaunchDate().toString();
    }
}