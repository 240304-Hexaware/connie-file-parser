package com.example.fileparser.services;

import com.example.fileparser.exceptions.ItemNotFoundException;
import com.example.fileparser.models.FlatFile;
import com.example.fileparser.models.SpecificationFile;
import com.example.fileparser.repositories.FlatFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;

@Service
public class FlatFileService {

    private FlatFileRepository flatFileRepository;

    @Autowired
    public FlatFileService(FlatFileRepository flatFileRepository){
        this.flatFileRepository = flatFileRepository;
    }

    /**
     * This reads the entire file at once with readString.
     * It is not intended for reading in large files.
     * @param flatFile - the flat file we want to read
     * @return - the String representation of the file
     * @throws IOException
     */
    public String readFlatFile(FlatFile flatFile) throws IOException {
        // same as readAllBytes
        File file = new File(flatFile.getFilePath());
        return Files.readString(file.toPath());
    }

    public FlatFile uploadFlatFile(String userId, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = "./src/files/" + fileName;
//        File savedFile = new File(filePath);
//        try(OutputStream os = new FileOutputStream(savedFile)) {
//            os.write(file.getBytes());
//        }
        // Create new FlatFile object
        FlatFile flatFile = new FlatFile(fileName, userId, filePath);

        // Save flat file to database
        return flatFileRepository.save(flatFile);
    }

    public void deleteFile(String fileId) {
        flatFileRepository.deleteById(fileId);
    }

    public List<FlatFile> getAllFilesByName(String fileName) throws ItemNotFoundException {
        return flatFileRepository.findByFileName(fileName).orElseThrow(() -> new ItemNotFoundException("No files matching this name."));
    }

    public List<FlatFile> getAllFilesByUser(String userId) throws ItemNotFoundException {
        return flatFileRepository.findByUserId(userId).orElseThrow(() -> new ItemNotFoundException("No files matching this name."));
    }

}
