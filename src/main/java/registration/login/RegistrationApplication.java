package registration.login;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import registration.login.model.Customer;
import registration.login.model.HotelBooking;
import registration.login.repository.CustomerRepository;
import registration.login.repository.HotelBookingRepository;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class RegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistrationApplication.class, args);
    }


    @Bean
    public CommandLineRunner demo(HotelBookingRepository hotelBookingRepository, CustomerRepository customerRepository) {
        return (args) -> {
//            Customer author = new Customer();
//            author.setFirstname("Sulemani");
//            author.setLastname("Bolande");
//            author.setPhone("08065529738");
//            author.setEmail("sylvester@gmail.com");
//            author.setPassword("1234567799");
//            author.setUsername("Cruza");
//            author.setId(1);
//            customerRepository.save(author);
//            HotelBooking hotelBooking=new HotelBooking();
//            hotelBooking.setBookDate(LocalDate.now());
//            hotelBooking.setRoomNumber("0071809252");
//            hotelBooking.setStatus("Booked");
//            hotelBooking.setType("STANDARD");
//            hotelBooking.setFormattedDate(LocalDate.now().toString());
//            hotelBooking.setId(1);
//
//            hotelBookingRepository.save(hotelBooking);
        };
    }
}
