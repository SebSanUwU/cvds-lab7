package co.edu.escuelaing.cvds.lab7;

import java.util.logging.Logger;
import co.edu.escuelaing.cvds.lab7.model.Configuration;
import co.edu.escuelaing.cvds.lab7.service.ConfigurationService;
import co.edu.escuelaing.cvds.lab7.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab7Application {

	Logger logger = Logger.getLogger(getClass().getName());
	private final ConfigurationService configurationService;
	private final EmployeeService employeeService;
	@Autowired
	public Lab7Application(ConfigurationService configurationService, EmployeeService employeeService) {
		this.configurationService = configurationService;
		this.employeeService = employeeService;
	}


	public static void main(String[] args) {
		SpringApplication.run(Lab7Application.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return (args) -> {

			logger.info("Adding Configurations....");
			configurationService.addConfiguration(new Configuration("price", "800000"));

			logger.info("\nGetting all configurations....");
			configurationService.getAllConfigurations().forEach(configuration -> logger.info(String.valueOf(configuration)));
		};
	}

}