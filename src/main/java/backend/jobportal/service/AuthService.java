package backend.jobportal.service;

import backend.jobportal.payload.LoginDto;
import backend.jobportal.payload.SignUpDto;

public interface AuthService {


    String login(LoginDto loginDto);
}
