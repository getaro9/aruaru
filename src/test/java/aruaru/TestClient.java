package aruaru;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class TestClient {

  private static final String rootUri = "http://localhost:8080/";

  private static RestTemplate restTemplate =
      new RestTemplateBuilder()
          .rootUri(rootUri)
          .defaultMessageConverters()
          .build();

  public static RestTemplate createRestTemplate() {
    return restTemplate;
  }
}
