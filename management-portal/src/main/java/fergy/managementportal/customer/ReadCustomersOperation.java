package fergy.managementportal.customer;

public interface ReadCustomersOperation {

  PagedCustomers getCustomers(int page, int size);
}
