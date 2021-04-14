package tables.model;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanAnnotation {
	@Bean(name="admin")
	Admin admin() {
		return new Admin();
	}
    
	@Bean(name="patient")
	Patient patient() {
		return new Patient();
	}
	
	@Bean(name="doc")
	Doctor doctor() {
		return new Doctor();
	}
	
	@Bean(name="appoint")
	DocAppointment docAppointment() {
		return new DocAppointment();
	}
	
}
