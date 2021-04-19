package registration.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import registration.login.model.HotelBooking;

@Repository
public interface HotelBookingRepository extends JpaRepository<HotelBooking, Integer> {
}
