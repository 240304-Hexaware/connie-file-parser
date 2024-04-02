package com.example.fileparser;

import com.example.fileparser.models.SpecificationFile;
import com.example.fileparser.parser.FileParser;
import com.example.fileparser.services.SpecificationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FileParserApplication  {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(FileParserApplication.class, args);

//		File file = new File("/Users/connietsang/file-parser/server/src/files/person.txt");
//		File file = new File("/Users/connietsang/file-parser/server/src/files/persons.txt");
//
//		FileParser fp = new FileParser();//
//
//		String fileToString = fp.readCompleteChars(file);
//		System.out.println("the complete string: " + fileToString);
//		System.out.println("string length " + fileToString.length());
//
//		File spec = new File("/Users/connietsang/file-parser/server/src/specs/personSpec.json");
//
//		Map map = fp.parseSpec(spec);
//
//		System.out.println(fp.parseFileToJson(fileToString, map));


	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/files").allowedOrigins("http://localhost:4200");
			}
		};
	}


}
