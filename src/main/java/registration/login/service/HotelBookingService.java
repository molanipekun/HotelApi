package registration.login.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import registration.login.dto.BookingCreation;
import registration.login.exceptions.ApiException;
import registration.login.model.HotelBooking;
import registration.login.repository.HotelBookingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class HotelBookingService {
    private final HotelBookingRepository bookingRepository;

    public HotelBookingService(final HotelBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public HotelBooking createHotelBooking(BookingCreation bookingCreation) {
        final HotelBooking booking = new HotelBooking();
        booking.setType(bookingCreation.getType());
        booking.setStatus(bookingCreation.getStatus());
        booking.setRoomNumber(bookingCreation.getRoomNumber());
        booking.setBookDate(LocalDate.now());
        return this.bookingRepository.save(booking);
    }

    @Transactional(readOnly = true)
    public List<HotelBooking> getAllHotelBookings() {
        return this.bookingRepository.findAll().stream().collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public HotelBooking getHotelBooking(final int id) {
        return this.bookingRepository.getOne(id);
    }

    public boolean deleteBook(int id) {
        HotelBooking book = bookingRepository.getOne(id);
        bookingRepository.delete(book);
        return true;
    }

    public HotelBooking updateBookPageCount(BookingCreation creation) {
        HotelBooking book = bookingRepository.getOne(creation.getId().intValue());
        if(book == null) {
                throw new ApiException("Hotel booking not found");
        }else{
            book.setType(creation.getType());
            book.setRoomNumber(creation.getRoomNumber());
            book.setStatus(creation.getStatus());
            bookingRepository.save(book);
        }
        return book;
    }
}
