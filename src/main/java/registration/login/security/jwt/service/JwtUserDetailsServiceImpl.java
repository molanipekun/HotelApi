package registration.login.security.jwt.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import registration.login.model.Customer;
import registration.login.repository.CustomerRepository;
import registration.login.security.jwt.JwtUserFactory;


@Service("jwtservice")
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(JwtUserDetailsServiceImpl.class);

    @Autowired
    private CustomerRepository agentRepository;

//    @Autowired
//    private IpAddressUtils addressUtils;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("Agent with ID [{}] logging in", username);
        Customer agent = agentRepository.findByUsername(username);
        final String ip = "";//addressUtils.getClientIP();
        if (agent == null) {
            logger.info("Could not find agentId [{}]", username);
            throw new UsernameNotFoundException(String.format("No agent found with username '%s'.", username));
        } else {
            logger.info("Found agentId [{}]", username);
            return JwtUserFactory.create(agent, ip);
        }
    }


}
