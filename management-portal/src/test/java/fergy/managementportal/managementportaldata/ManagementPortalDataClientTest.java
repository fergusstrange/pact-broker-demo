package fergy.managementportal.managementportaldata;

import static org.assertj.core.api.Assertions.assertThat;

import au.com.dius.pact.consumer.ConsumerPactBuilder;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import fergy.managementportal.ClientTest;
import fergy.managementportal.managementportal.ManagementPortalDataClient;
import fergy.managementportal.managementportal.PagedCustomerIdentities;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = "application.customer-identity-base-url=http://localhost:8888")
public class ManagementPortalDataClientTest extends ClientTest {

  @Autowired
  private ManagementPortalDataClient managementPortalDataClient;

  @Rule
  public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("managementPortalData",
      "localhost", 8888, this);

  @Pact(consumer = "managementPortal")
  public RequestResponsePact returnCustomers(PactDslWithProvider provider) {
    return provider
        .given("At least one customer exists")
        .uponReceiving("A paged request for all customers")
        .path("/management-portal-data-api/customers")
        .method("GET")
        .willRespondWith()
        .status(200)
        .matchHeader("Content-Type", "application/json;charset=UTF-8")
        .body(ConsumerPactBuilder.jsonBody()
            .eachLike("customers")
            .uuid("id")
            .stringType("firstName", "Tom")
            .stringType("lastName", "Beresford")
            .stringType("email", "tom.beresford@beer.com"))
        .toPact();
  }

  @Test
  @PactVerification(fragment = "returnCustomers")
  public void willReturnCustomersWhenGetRequestMade() throws Exception {
    PagedCustomerIdentities pagedCustomers = managementPortalDataClient.fetchCustomers();

    assertThat(pagedCustomers.getCustomers()).isNotEmpty();
    assertThat(pagedCustomers.getCustomers().stream())
        .allSatisfy(customerIdentity ->
            assertThat(customerIdentity).hasNoNullFieldsOrProperties());
  }
}
