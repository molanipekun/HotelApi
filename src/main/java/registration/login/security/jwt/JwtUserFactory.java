package registration.login.security.jwt;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import registration.login.model.Customer;

import java.util.List;


public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(Customer user, String ipAddress) {
        return new JwtUser(
                (long) user.getId(),
                user.getUsername(),
                user.getPassword(),
                ipAddress,
                mapToGrantedAuthorities(user),
                true
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Customer user) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.NO_AUTHORITIES;
        return grantedAuthorities;
    }
}
