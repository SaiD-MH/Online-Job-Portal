package backend.jobportal.payload;


import lombok.*;

import java.nio.file.Paths;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeProfileResponse {
    private int id;
    private String photoPath;
    private String github;
    private String linkedin;
    private String bio;
    private String website;
    private String resumePath;


    public String getPhotoPath() {

        return Paths.get(photoPath).getFileName().toString();
    }

    public String getResumePath() {

        return Paths.get(resumePath).getFileName().toString();
    }

}