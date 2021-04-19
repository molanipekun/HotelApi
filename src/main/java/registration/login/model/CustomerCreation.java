package registration.login.model;

import lombok.Data;

@Data
public class CustomerCreation {
    final Long id;
    final String username;
    final String firstname;
    final String lastname;
    final String email;
    final String phone;
    private String password;
}
