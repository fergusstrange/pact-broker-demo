package fergy.managementportal;

import com.fasterxml.jackson.databind.ObjectMapper;
import fergy.managementportal.managementportal.ManagementPortalDataClient;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfiguration {

  @Bean
  public ManagementPortalDataClient customerIdentityClient(
      @Value("${application.customer-identity-base-url}") String customerIdentityBaseUrl,
      ObjectMapper objectMapper) {
    return feignClient(customerIdentityBaseUrl, ManagementPortalDataClient.class, objectMapper);
  }

  private static <T> T feignClient(String baseUrl, Class<T> clientClass,
      ObjectMapper objectMapper) {
    return Feign.builder()
        .decoder(new JacksonDecoder(objectMapper))
        .encoder(new JacksonEncoder(objectMapper))
        .requestInterceptor(x -> x
            .header("Accept", "application/json; charset=UTF-8")
            .header("Content-Type", "application/json; charset=UTF-8"))
        .target(clientClass, baseUrl);
  }
}
