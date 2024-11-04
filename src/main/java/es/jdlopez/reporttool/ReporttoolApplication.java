package es.jdlopez.reporttool;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.jdlopez.reporttool.domain.RTConfiguration;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class ReporttoolApplication {

	private ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) {
		SpringApplication.run(ReporttoolApplication.class, args);
	}

	@Bean
	public RTConfiguration config(ApplicationArguments args) throws IOException {
		RTConfiguration ret = new RTConfiguration();
		if (args != null && args.getSourceArgs() != null && args.getSourceArgs().length > 0) {
			File cfgFile = new File(args.getSourceArgs()[0]);
			if (cfgFile.exists()) {
				ret = mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
						.readValue(cfgFile, RTConfiguration.class);
			}
		}
		return ret;
	}

}
