package fergy.managementportal.managementportal;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Customer {

  private final UUID id;
  private final String firstName;
  private final String lastName;
  private final String email;
}
