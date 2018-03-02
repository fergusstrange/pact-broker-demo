package fergy.managementportaldata.customer;


import java.util.Collections;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("customers")
public class CustomerController {

  @GetMapping
  public PagedCustomers fetchCustomers() {
    return PagedCustomers.builder()
        .customers(Collections.singletonList(
            PagedCustomer.builder()
                .email("tom.beer@aa.com")
                .firstName("Tom")
                .lastName("Beersford")
                .id(UUID.randomUUID())
                .build()))
        .build();
  }
}
