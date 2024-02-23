package backend.jobportal.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveToDisk {


    public static void saveFile(MultipartFile fileToSave, Path fullPath) throws IOException {

        fileToSave.transferTo(fullPath);

    }

}
