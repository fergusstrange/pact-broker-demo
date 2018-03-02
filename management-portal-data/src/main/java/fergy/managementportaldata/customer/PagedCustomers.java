package fergy.managementportaldata.customer;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PagedCustomers {

  private final List<PagedCustomer> customers;

}
