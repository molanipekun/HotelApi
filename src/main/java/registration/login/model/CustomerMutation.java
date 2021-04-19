//package registration.login.model;
//
//import com.coxautodev.graphql.tools.GraphQLMutationResolver;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import registration.login.service.CustomerService;
//import registration.login.service.HotelBookingService;
//
//@Component
//public class CustomerMutation implements GraphQLMutationResolver {
//    @Autowired
//    private CustomerService customerService;
//    public Customer createCustomer(CustomerCreation customerCreation) {
//        return this.customerService.createUser(customerCreation);
//    }
//}