package com.example.fileparser.controllers;

import com.example.fileparser.exceptions.ItemAlreadyExistsException;
import com.example.fileparser.exceptions.ItemNotFoundException;
import com.example.fileparser.models.SpecificationFile;
import com.example.fileparser.services.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class SpecificationController {
    private SpecificationService specificationService;

    @Autowired
    public SpecificationController(SpecificationService specificationService) {
        this.specificationService = specificationService;
    }

    @GetMapping("/specs")
    @ResponseStatus(HttpStatus.OK)
    public List<SpecificationFile> getAllSpecifications(){
        return specificationService.getAllSpecifications();
    }


    // upload file
    @PostMapping("spec")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SpecificationFile uploadSpecification(@RequestParam String userId, @RequestParam ("specFile") MultipartFile specFile) throws IOException, ItemAlreadyExistsException {
        return specificationService.uploadSpecificationFile(userId, specFile);
    }

}
