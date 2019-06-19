package simple.faculte.admission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("simple.faculte.admission.rest")

public class AdmissionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdmissionApplication.class, args);
	}

}
