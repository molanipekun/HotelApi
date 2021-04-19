package registration.login.dto;

import lombok.Data;

@Data
public class BookingCreation {
    final Long id;
    final String type;
    final String modelCode;
    final String brandName;
    final String launchDate;
    final String status;
    final String roomNumber;
}
