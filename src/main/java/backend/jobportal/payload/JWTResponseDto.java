package backend.jobportal.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWTResponseDto {

    private String accessToken;
    private String tokenType = "Bearer";


}