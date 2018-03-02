package fergy.managementportal.cdc;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import fergy.managementportal.customer.PagedCustomer;
import fergy.managementportal.customer.PagedCustomers;
import fergy.managementportal.customer.ReadCustomersOperation;
import java.util.Collections;
import java.util.UUID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("service-stubbed")
public class ServiceStubbedConfiguration {

  @Bean
  @Primary
  public ReadCustomersOperation viewCustomersOperation() {
    ReadCustomersOperation readCustomersOperation = mock(ReadCustomersOperation.class);

    when(readCustomersOperation.getCustomers(anyInt(), anyInt()))
        .thenReturn(
            PagedCustomers.builder()
                .customers(Collections.singletonList(
                    PagedCustomer.builder()
                        .email("tom.beer@aa.com")
                        .firstName("Tom")
                        .lastName("Beersford")
                        .id(UUID.randomUUID())
                        .build())).build());

    return readCustomersOperation;
  }
}
