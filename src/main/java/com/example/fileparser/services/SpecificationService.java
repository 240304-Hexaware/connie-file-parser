package com.example.fileparser.services;

import com.example.fileparser.exceptions.ItemAlreadyExistsException;
import com.example.fileparser.exceptions.ItemNotFoundException;
import com.example.fileparser.models.Field;
import com.example.fileparser.models.SpecificationFile;
import com.example.fileparser.repositories.SpecificationRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class SpecificationService {

    private final SpecificationRepository specificationRepository;

    @Autowired
    public SpecificationService(SpecificationRepository specificationRepository) {
        this.specificationRepository = specificationRepository;
    }

    public List<SpecificationFile> getAllSpecifications(){
        return specificationRepository.findAll();
    }

    /*** Parses a specification JSON file into a map of tokens
     *
     * @param specificationFile - the specification JSON file
     * @return a map of tokens, mapping field name to Field
     * @throws IOException
     */
    public static Map<String, Field> parseSpec(SpecificationFile specificationFile) throws IOException {
        File file = new File(specificationFile.getFilePath());
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Field> map = mapper.readValue(file, new TypeReference<Map<String, Field>>() {});

        Set<String> fieldNames = map.keySet();
        for(String fieldName : fieldNames) {
            map.get(fieldName).setName(fieldName);
        }
        return map;
    }

    public SpecificationFile uploadSpecificationFile(String userId, MultipartFile specFile) throws IOException, ItemAlreadyExistsException{
        String fileName = specFile.getOriginalFilename();
        if (specificationRepository.existsByName(fileName)){
            throw new ItemAlreadyExistsException("there is already a spec file by name: " + fileName);
        }
        String filePath = "./src/specs/" + fileName;
        File savedFile = new File(filePath);
        //convert MultipartFile specFile to File savedFile and save to given path
        try(OutputStream os = new FileOutputStream(savedFile)) {
            os.write(specFile.getBytes());
        }
        SpecificationFile specificationFile = new SpecificationFile(userId, filePath, fileName);
        return specificationRepository.save(specificationFile);
    }

    public SpecificationFile getSpecificationFile(String specName) throws ItemNotFoundException{
        return specificationRepository.findByName(specName).orElseThrow(
                () -> new ItemNotFoundException("No such specification file")
        );
    }

}
