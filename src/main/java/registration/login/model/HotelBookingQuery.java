//package registration.login.model;
//
//import com.coxautodev.graphql.tools.GraphQLQueryResolver;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import registration.login.service.HotelBookingService;
//
//import java.util.List;
//import java.util.Optional;
//@Component
//public class HotelBookingQuery implements GraphQLQueryResolver {
//    @Autowired
//    private HotelBookingService vehicleService;
//    public List<HotelBooking> getBookings() {
//        return this.vehicleService.getAllHotelBookings();
//    }
//    public HotelBooking getBooking(final int id) {
//        return this.vehicleService.getHotelBooking(id);
//    }
//
//}