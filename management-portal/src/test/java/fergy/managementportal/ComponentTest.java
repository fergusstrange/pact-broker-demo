package fergy.managementportal;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import java.io.IOException;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(properties = "management.port=0")
public abstract class ComponentTest {

  @Autowired
  private WebApplicationContext context;
  @Autowired
  private ObjectMapper objectMapper;

  protected MockMvc mockMvc;

  @Before
  public void setUpMockMvc() {
    this.mockMvc = webAppContextSetup(context)
        .build();
  }

  protected String resource(String resourceName) throws IOException {
    return Resources.toString(Resources.getResource(resourceName),
        Charsets.UTF_8);
  }

  protected String asJson(Object o) {
    try {
      return objectMapper.writeValueAsString(o);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
