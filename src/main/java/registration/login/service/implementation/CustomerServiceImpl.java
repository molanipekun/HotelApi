//package registration.login.service.implementation;
//
//import org.modelmapper.ModelMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.context.i18n.LocaleContextHolder;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.mobile.device.Device;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import registration.login.dto.ApiUser;
//import registration.login.dto.ChangePassword;
//import registration.login.dto.CustomerDTO;
//import registration.login.dto.CustomerData;
//import registration.login.exceptions.*;
//import registration.login.model.Customer;
//import registration.login.model.LoginResponse;
//import registration.login.model.SignUpResponse;
//import registration.login.repository.CustomerRepository;
//import registration.login.service.CustomerService;
//import registration.login.service.PasswordPolicyService;
//import registration.login.utils.ApiUtil;
//import registration.login.utils.DateFormatter;
//
//import javax.transaction.Transactional;
//import java.math.BigDecimal;
//import java.util.*;
//
//@Service
//public class CustomerServiceImpl implements CustomerService {
//    ModelMapper modelMapper;
//
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    private Locale locale = LocaleContextHolder.getLocale();
//
//    private BCryptPasswordEncoder passwordEncoder;
//
//    private PasswordPolicyService passwordPolicyService;
//
//    MessageSource messageSource;
//
//    CustomerRepository customerRepository;
//
//    @Autowired
//    public CustomerServiceImpl(ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder, PasswordPolicyService passwordPolicyService, MessageSource messageSource, CustomerRepository customerRepository) {
//        this.modelMapper = modelMapper;
//        this.passwordEncoder = passwordEncoder;
//        this.passwordPolicyService = passwordPolicyService;
//        this.messageSource = messageSource;
//        this.customerRepository = customerRepository;
//    }
//    @Autowired
//    ApiUtil apiUtil;
//
//    @Override
//    public Object validateUser(ApiUser apiUser, Device device) {
//        return apiUtil.validateUser(apiUser,device);
//    }
//
//    @Override
//    public Customer findById(Long id) {
//        return this.customerRepository.getOne(id);
//    }
//
//    @Override
//    public Customer findByEmail(String email) {
//        return this.customerRepository.findFirstByEmailIgnoreCase(email);
//    }
//
//    @Override
//    public CustomerDTO getUserById(Long id) {
//        Customer users = this.customerRepository.getOne(id);
//        return convertEntityToDTO(users);
//    }
//
//    @Override
//    public CustomerDTO getUserByEmail(String email) {
//        Customer users = this.customerRepository.findFirstByEmailIgnoreCase(email);
//        return convertEntityToDTO(users);
//    }
//
//
//
//    @Override
//    public List<CustomerDTO> getCustomer() {
//        List<Customer> usersList = this.customerRepository.findAll();
//        return convertEntitiesToDTOs(usersList);
//    }
//
//    @Override
//    public Page<CustomerDTO> getCustomer(Pageable pageDetails) {
//        Page<Customer> page = customerRepository.findAll(pageDetails);
//        List<CustomerDTO> dtOs = convertEntitiesToDTOs(page.getContent());
//        long t = page.getTotalElements();
//        Page<CustomerDTO> pageImpl = new PageImpl<CustomerDTO>(dtOs, pageDetails, t);
//        return pageImpl;
//    }
//
//
//
//
//
//    @Override
//    public LoginResponse addCustomer(CustomerData customerData) throws ApiException {
//
//        Customer user = customerRepository.findFirstByEmailIgnoreCase(customerData.getEmail());
//
//        if (user != null) {
//            throw new DuplicateObjectException(messageSource.getMessage("user.exist", null, locale));
//        }
//        try {
//            CustomerDTO customerDTO = modelMapper.map(customerData, CustomerDTO.class);
//            user = convertDTOToEntity(customerDTO);
//            user.setActive("Y");
//
//            user.setCreatedOnDate(new Date());
//            user.setPassword(passwordEncoder.encode(customerData.getPassword()));
//            Customer customer = customerRepository.save(user);
//            convertEntityToDTO(customer);
//            LoginResponse signUpResponse = new LoginResponse("00", "Successful");
//            return signUpResponse;
//        }catch (ApiException e){
//            LoginResponse signUpResponse=new LoginResponse("99",e.getMessage());
//            return signUpResponse;
//        }
//
//    }
//
//
//    @Override
//    @Transactional
//    public LoginResponse changePassword(ChangePassword changePassword) throws PasswordException {
//
//        Customer customer = customerRepository.findByUserName(changePassword.getUsername());
//        if(customer==null){
//            LoginResponse w=new LoginResponse("14","Usename not found");
//            return w;
//        }
//
//        if (!passwordEncoder.matches(customer.getPassword(), changePassword.getOldPassword())) {
//            LoginResponse x=new LoginResponse("12","Wrong password");
//            return x;
//        }
//
//
//        if (!changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
//            LoginResponse y=new LoginResponse("13","Password Mismatch");
//            return y;
//        }
//
//        try {
//
//            customer.setPassword(this.passwordEncoder.encode(changePassword.getNewPassword()));
//            this.customerRepository.save(customer);
//            logger.info("User {} password has been updated", changePassword.getUsername());
//            LoginResponse z=new LoginResponse("00","Successful");
//            return z;
//        } catch (Exception e) {
//            LoginResponse x=new LoginResponse("99",e.getMessage());
//            return x;
//        }
//    }
//
//    @Override
//    @Transactional
//    public String resetPassword(Long userId) throws PasswordException {
//
//
//        Customer user = customerRepository.getOne(userId);
//        if ("N".equals(user.getActive())) {
//            throw new ApiException(messageSource.getMessage("users.deactivated", null, locale));
//        }
//
//        try {
//            String newPassword = passwordPolicyService.randomString(10);
//            user.setPassword(passwordEncoder.encode(newPassword));
//
//            String fullName = user.getFirstName() + " " + user.getLastName();
//            customerRepository.save(user);
//            //todo implement mail sending
////            Email email = new Email.Builder()
////                    .setRecipient(user.getEmail())
////                    .setSubject(messageSource.getMessage("user.password.reset.subject", null, locale))
////                    .setBody(String.format(messageSource.getMessage("user.password.reset.message", null, locale), fullName, newPassword))
////                    .build();
////            mailService.sendGrid(email);
//            logger.info("User {} password reset successfully", user.getEmail());
//            return messageSource.getMessage("password.reset.success", null, locale);
//        } catch (ApiException me) {
//            throw new PasswordException(messageSource.getMessage("mail.failure", null, locale), me);
//        } catch (Exception e) {
//            throw new PasswordException(messageSource.getMessage("password.reset.failure", null, locale), e);
//        }
//    }
//
//    @Override
//    public String deleteCustomer(Long id) throws ApiException {
//        try {
//            customerRepository.delete(id);
//            logger.info("User {} has been deleted", id.toString());
//            return messageSource.getMessage("success", null, locale);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ApiException(messageSource.getMessage("failure", null, locale));
//        }
//    }
//
//
//
//    public Customer convertDTOToEntity(CustomerDTO userDTO) {
//        Customer users = modelMapper.map(userDTO, Customer.class);
//        return users;
//    }
//
//    public CustomerDTO convertEntityToDTO(Customer users) {
//        CustomerDTO userDTO = modelMapper.map(users, CustomerDTO.class);
//
//
//        if(users.getCreatedOnDate()!=null){
//            userDTO.setCreatedOnDate(DateFormatter.format(users.getCreatedOnDate()));
//        }
//
//        return userDTO;
//    }
//
//    public List<CustomerDTO> convertEntitiesToDTOs(Iterable<Customer> usersIterable) {
//        List<CustomerDTO> userDTOList = new ArrayList<>();
//        for (Customer users : usersIterable) {
//            CustomerDTO userDTO = convertEntityToDTO(users);
//            userDTOList.add(userDTO);
//        }
//        return userDTOList;
//    }
//}
