package com.gittx.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    public String storeFile(MultipartFile [] files) throws Exception {

        for (MultipartFile file : files) {


            final Path fileStorageLocation = Paths.get("D:\\personal-projects\\kyc")
                    .toAbsolutePath().normalize();


            // Normalize file name
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            try {

                // Only allow pdf,jpg, jpeg and png
                if (!(
                        (file.getContentType().equalsIgnoreCase("application/pdf")) ||
                                (file.getContentType().equalsIgnoreCase("image/jpeg")) ||
                                (file.getContentType().equalsIgnoreCase("image/png"))
                )) {
                    throw new Exception("File Type is not Allowed");
                }

                // Check if the file's name contains invalid characters
                // TODO Handle more invalid file names
                if (fileName.contains("..")) {
                    throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
                }

                // At this point, add uuid to filename to make it unique
                fileName = String.format("%s-%s.%s", fileName.split("\\.")[0], UUID.randomUUID(), fileName.split("\\.")[1]);

                // Copy file to the target location (Replacing existing file with the same name)
                Path targetLocation = fileStorageLocation.resolve(fileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Attachment Uploaded Successfully on location - " + targetLocation);
                return targetLocation.toString();

            } catch (Exception ex) {
                throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
            }
        }
        return ("Could not process multipartfiles or store file. Please try again!");
    }

}
