package fergy.managementportal.session;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Email;

@Value
@Builder
@AllArgsConstructor
public class CreateSession {
  @NotNull
  @Email
  private final String email;
  @NotNull
  private final String password;
}
