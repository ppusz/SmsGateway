package pusz.name.smsgateway;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class SmsgatewayApplication {

	private static final Logger logger = Logger.getLogger(SmsgatewayApplication.class);


	public static void main(String[] args) {

		logger.debug("Starting application...");
		SpringApplication.run(SmsgatewayApplication.class, args);
	}
}
