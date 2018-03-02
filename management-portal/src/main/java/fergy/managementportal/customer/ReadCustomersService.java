package fergy.managementportal.customer;

import static java.util.stream.Collectors.toList;

import fergy.managementportal.managementportal.ManagementPortalDataClient;
import fergy.managementportal.managementportal.PagedCustomerIdentities;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReadCustomersService implements ReadCustomersOperation {

  private final ManagementPortalDataClient managementPortalDataClient;

  @Override
  public PagedCustomers getCustomers(int page, int size) {
    PagedCustomerIdentities pagedCustomerIdentities = managementPortalDataClient
        .fetchCustomers();

    List<PagedCustomer> pagedCustomers = pagedCustomerIdentities.getCustomers()
        .stream()
        .map(customerIdentity -> PagedCustomer.builder()
            .id(customerIdentity.getId())
            .firstName(customerIdentity.getFirstName())
            .lastName(customerIdentity.getLastName())
            .email(customerIdentity.getEmail())
            .build())
        .collect(toList());

    return PagedCustomers.builder().customers(pagedCustomers).build();
  }
}
