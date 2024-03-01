package backend.jobportal.controller;

import backend.jobportal.payload.JWTResponseDto;
import backend.jobportal.payload.LoginDto;
import backend.jobportal.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "REST APIs for Auth Resource")
public class AuthController {


    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTResponseDto> authenticateUser(@RequestBody LoginDto loginDto) {

        JWTResponseDto jwtResponseDto = new JWTResponseDto();


        String token = authService.login(loginDto);
        jwtResponseDto.setAccessToken(token);


        return ResponseEntity.ok(jwtResponseDto);
    }

}