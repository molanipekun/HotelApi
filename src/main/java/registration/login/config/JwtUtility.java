package registration.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import registration.login.model.Customer;
import registration.login.repository.CustomerRepository;


@Service
public class JwtUtility {

    @Autowired
    CustomerRepository customerRepository;


    private BCryptPasswordEncoder passwordEncoder;

    public Customer getCurrentAgent(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        Customer agents = null;
        if(authentication.getName()!=null){
            agents = customerRepository.findByUsername(authentication.getName());
        }
        return agents;
    }


//    public boolean isValidAgentPin(String requestPin,Agent agents){
//        if(!passwordEncoder.matches(requestPin,agents.getUserPin())){
//            return false;
//        }else{
//            return true;
//        }
//    }

}
