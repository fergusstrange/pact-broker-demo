package fergy.managementportal;

public class ServiceStubbedApplication extends Application {

  public static void main(String... args) {
    System.setProperty("spring.profiles.active", "service-stubbed");
    Application.main(args);
  }
}