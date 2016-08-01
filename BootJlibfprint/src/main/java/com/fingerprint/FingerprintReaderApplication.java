package com.fingerprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FingerprintReaderApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(FingerprintReaderApplication.class, args);
		//System.err.println(System.getProperty("java.library.path"));
	}
	
}
