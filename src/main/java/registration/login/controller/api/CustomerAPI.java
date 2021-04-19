package registration.login.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import registration.login.config.JwtUtility;
import registration.login.exceptions.ApiException;
import registration.login.model.Customer;
import registration.login.model.CustomerCreation;
import registration.login.model.Response;
import registration.login.service.CustomerService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/")
@RestController
public class CustomerAPI {
    Logger logger = LoggerFactory.getLogger(this.getClass());


    private CustomerService customerService;
    private JwtUtility jwtUtility;

    @Autowired
    public CustomerAPI(CustomerService customerService, JwtUtility jwtUtility) {
        this.customerService = customerService;
        this.jwtUtility = jwtUtility;
    }

    @PostMapping("hotel/reservation/createuser")
    public Response createUser(@RequestBody CustomerCreation customerCreation) {
        Response response = new Response();
        try {
            response.setRespCode("00");
            response.setRespDescription("success");
            response.setRespBody(customerService.createUser(customerCreation));
            return response;
        }
        catch (ApiException e) {
            response.setRespDescription(e.getMessage());
            response.setRespCode("999");
            return response;
        }

    }

    @PostMapping("v1/hotel/reservation/login")
    public Response login(@RequestBody LoginDto loginDto) {
        Response response = new Response();
        try {
            response.setRespBody("00");
            response.setRespDescription("success");
            response.setRespBody(customerService.login(loginDto));
            return response;
        }
        catch (Exception e) {
            response.setRespDescription(e.getMessage());
            response.setRespCode("999");
            return response;
        }

    }

    @GetMapping("v1/hotel/reservation/{id}/getCustomer")
    public Response getCustomer(@PathVariable Long id) {
        Response response=new Response();
        Customer agent = jwtUtility.getCurrentAgent();

        if(agent ==null){
            throw new ApiException("User Not Found in Token");
        }
        response.setRespCode("00");
        response.setRespDescription("success");
        response.setRespBody(customerService.getCustomer(id));
        return response;
    }

    @GetMapping("v1/hotel/reservation/allCustomer")
    public Response getAllCustomer() {
        Response response=new Response();
        Customer agent = jwtUtility.getCurrentAgent();

        if(agent ==null){
            throw new ApiException("User Not Found in Token");
        }
        response.setRespCode("00");
        response.setRespDescription("success");
        response.setRespBody(customerService.getAllCustomer());
        return response;
    }

    @GetMapping("v1/hotel/reservation/{id}/deleteCustomer")
    public Response deleteCustomer(@PathVariable Long id) {
        Response response=new Response();
        Customer agent = jwtUtility.getCurrentAgent();

        if(agent ==null){
            throw new ApiException("User Not Found in Token");
        }
        response.setRespCode("00");
        response.setRespDescription("success");
        response.setRespBody(customerService.deleteCustomer(id));
        return response;
    }

    @GetMapping("v1/hotel/reservation/updateCustomer")
    public Response updateCustomer(@RequestBody CustomerCreation customerCreation) {
        Response response=new Response();
        Customer agent = jwtUtility.getCurrentAgent();

        if(agent ==null){
            throw new ApiException("User Not Found in Token");
        }
        response.setRespCode("00");
        response.setRespDescription("success");
        response.setRespBody(customerService.updateBookPageCount(customerCreation));
        return response;
    }
}
