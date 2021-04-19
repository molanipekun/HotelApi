package registration.login.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import registration.login.config.JwtUtility;
import registration.login.dto.BookingCreation;
import registration.login.exceptions.ApiException;
import registration.login.model.Customer;
import registration.login.model.Response;
import registration.login.service.HotelBookingService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/")
@RestController
public class BookingAPI {
    private HotelBookingService hotelBookingService;
    private JwtUtility jwtUtility;

    @Autowired
    public BookingAPI(HotelBookingService hotelBookingService, JwtUtility jwtUtility) {
        this.hotelBookingService = hotelBookingService;
        this.jwtUtility = jwtUtility;
    }


    @PostMapping("hotel/reservation/createBooking")
    public Response createBooking(@RequestBody BookingCreation customerCreation) {
        Response response = new Response();
        try {
            response.setRespCode("00");
            response.setRespBody("success");
            response.setRespBody(hotelBookingService.createHotelBooking(customerCreation));
            return response;
        }
        catch (Exception e) {
            response.setRespDescription(e.getMessage());
            response.setRespCode("999");
            return response;
        }

    }


    @GetMapping("v1/hotel/reservation/{id}/getHotelBooking")
    public Response getCustomer(@PathVariable int id) {
        Response response=new Response();
        Customer agent = jwtUtility.getCurrentAgent();

        if(agent ==null){
            throw new ApiException("User Not Found in Token");
        }
        response.setRespCode("00");
        response.setRespDescription("success");
        response.setRespBody(hotelBookingService.getHotelBooking(id));
        return response;
    }

    @GetMapping("v1/hotel/reservation/allBookingHistory")
    public Response getAllCustomer() {
        Response response=new Response();
        Customer agent = jwtUtility.getCurrentAgent();

        if(agent ==null){
            throw new ApiException("User Not Found in Token");
        }
        response.setRespCode("00");
        response.setRespDescription("success");
        response.setRespBody(hotelBookingService.getAllHotelBookings());
        return response;
    }

    @GetMapping("v1/hotel/reservation/{id}/deleteBooking")
    public Response deleteCustomer(@PathVariable int id) {
        Response response=new Response();
        Customer agent = jwtUtility.getCurrentAgent();

        if(agent ==null){
            throw new ApiException("User Not Found in Token");
        }
        response.setRespCode("00");
        response.setRespDescription("success");
        response.setRespBody(hotelBookingService.deleteBook(id));
        return response;
    }

    @GetMapping("v1/hotel/reservation/{id}/update")
    public Response updateCustomer(@RequestBody BookingCreation customerCreation) {
        Response response=new Response();
        Customer agent = jwtUtility.getCurrentAgent();

        if(agent ==null){
            throw new ApiException("User Not Found in Token");
        }
        response.setRespCode("00");
        response.setRespDescription("success");
        response.setRespBody(hotelBookingService.updateBookPageCount(customerCreation));
        return response;
    }
}
