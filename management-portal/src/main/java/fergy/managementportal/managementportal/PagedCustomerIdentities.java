package fergy.managementportal.managementportal;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class PagedCustomerIdentities {

  private List<Customer> customers;
}
