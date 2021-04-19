package registration.login.dto;

import lombok.Data;

@Data
public class LoginRespDTO {
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String phone;
    private String email;
    private String token;
}
