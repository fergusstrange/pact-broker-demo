package fergy.managementportaldata.customer;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PagedCustomer {

  private final UUID id;
  private final String firstName;
  private final String lastName;
  private final String email;
}
