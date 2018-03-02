package fergy.managementportal.session;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CreateSessionService implements CreateSessionOperation {

  @Override
  public String create(CreateSession createSession) {
    return "theSessionIsWorking";
  }
}
