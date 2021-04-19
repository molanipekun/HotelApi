//package registration.login.model;
//
//import com.coxautodev.graphql.tools.GraphQLMutationResolver;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import registration.login.dto.BookingCreation;
//import registration.login.service.HotelBookingService;
//
//import java.util.Optional;
//
//@Component
//public class HotelBookingMutation implements GraphQLMutationResolver {
//    @Autowired
//    private HotelBookingService bookingService;
//    public HotelBooking createBooking(BookingCreation bookingCreation) {
//        return this.bookingService.createHotelBooking(bookingCreation);
//    }
//    public boolean deleteBook(int id) {
//        bookingService.deleteBook(id);
//        return true;
//    }
//
//    public HotelBooking updateBookPageCount(BookingCreation bookingCreation) {
//        HotelBooking book = bookingService.updateBookPageCount(bookingCreation);
//        return book;
//    }
//}