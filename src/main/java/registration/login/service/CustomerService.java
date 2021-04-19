package registration.login.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import registration.login.config.JwtUtility;
import registration.login.controller.api.LoginDto;
import registration.login.dto.LoginRespDTO;
import registration.login.exceptions.ApiException;
import registration.login.model.Customer;
import registration.login.model.CustomerCreation;
import registration.login.model.Response;
import registration.login.repository.CustomerRepository;
import registration.login.security.jwt.JwtTokenUtil;
import sun.management.resources.agent;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    private JwtTokenUtil jwtTokenUtil;
    Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CustomerService(final CustomerRepository customerRepository, JwtTokenUtil jwtTokenUtil) {
        this.customerRepository = customerRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Transactional
    public Customer createUser(CustomerCreation customerCreation) {
        System.out.println("here me: "+customerCreation);
        final Customer customer = new Customer();
//        customer.setPassword(passwordEncoder.encode(customerCreation.getPassword()));
        customer.setEmail(customerCreation.getEmail());
        customer.setUsername(customerCreation.getUsername());
        customer.setLastname(customerCreation.getLastname());
        customer.setPhone(customerCreation.getPhone());
        customer.setFirstname(customerCreation.getFirstname());
        return this.customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public List<Customer> getAllCustomer() {
        return this.customerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Customer> getCustomer(final Long id) {
        return this.customerRepository.findById(id);
    }

    public boolean deleteCustomer(Long id) {
        Customer customer = customerRepository.getOne(id);
        customerRepository.delete(customer);
        return true;
    }

    public Optional<Customer> updateBookPageCount(CustomerCreation creation) {
        Customer customer = customerRepository.getOne(creation.getId());
        if(customer == null) {
            throw new ApiException("Customer Not Found ");
        }
        else {
            customer.setUsername(creation.getUsername());
            customer.setEmail(creation.getEmail());
            customer.setPhone(creation.getPhone());
            customer.setLastname(creation.getLastname());
            customer.setFirstname(creation.getFirstname());
            customerRepository.save(customer);
        }
        return Optional.of(customer);
    }

    public Response login(LoginDto loginDto) {
        Customer customer = customerRepository.findByUsername(loginDto.getUsername());
        logger.info("############# SECURITY CHECK SUCCESS, AUTHORIZING USER");

        if (Objects.isNull(customer)) {
                throw new ApiException("Invalid Agent ID");
        }
        if (!passwordEncoder.matches(loginDto.getPassword(), customer.getPassword())) {
            throw new ApiException("Invalid Customer Password");
        }
        logger.info("############# AUTHENTICATION FINALIZED ----");

        LoginRespDTO loginRespDTO = new LoginRespDTO();
        loginRespDTO.setEmail(customer.getEmail());
        loginRespDTO.setFirstname(customer.getFirstname());
        loginRespDTO.setLastname(customer.getLastname());
        loginRespDTO.setPassword(customer.getPassword());
        loginRespDTO.setPhone(customer.getPhone());
        loginRespDTO.setUsername(customer.getUsername());
        loginRespDTO.setToken(jwtTokenUtil.getToken(customer.getUsername()));
        Response response = new Response();
        response.setRespCode("00");
        response.setRespBody(loginRespDTO);
        response.setRespDescription("success");
        return response;
    }
}
