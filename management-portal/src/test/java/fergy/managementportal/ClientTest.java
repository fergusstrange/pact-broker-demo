package fergy.managementportal;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = NONE)
public abstract class ClientTest {
}