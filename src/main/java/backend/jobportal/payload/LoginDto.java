package backend.jobportal.payload;


import lombok.Data;

@Data
public class LoginDto {

    private String email;
    private String password;
    private String role;

}
