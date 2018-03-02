package fergy.managementportal.session;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("session")
public class SessionController {

  @PostMapping
  public void createSession(@RequestBody @Valid CreateSession createSession,
      HttpServletResponse httpServletResponse) {
    httpServletResponse.addCookie(new Cookie("youLoggedIn", createSession.toString()));
  }
}
