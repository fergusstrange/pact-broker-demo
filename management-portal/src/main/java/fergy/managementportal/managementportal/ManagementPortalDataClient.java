package fergy.managementportal.managementportal;

import feign.RequestLine;

public interface ManagementPortalDataClient {

  @RequestLine("GET /management-portal-data-api/customers")
  PagedCustomerIdentities fetchCustomers();

}
