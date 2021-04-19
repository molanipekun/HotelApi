//package registration.login.model;
//
//import com.coxautodev.graphql.tools.GraphQLQueryResolver;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import registration.login.service.CustomerService;
//import registration.login.service.HotelBookingService;
//
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class CustomerQuery implements GraphQLQueryResolver {
//    @Autowired
//    private CustomerService customerService;
//    public List<Customer> getCustomers() {
//        return this.customerService.getAllCustomer();
//    }
//    public Optional<Customer> getCustomer(final Long id) {
//        return this.customerService.getCustomer(id);
//    }
//
//}