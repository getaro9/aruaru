package aruaru;

import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class TestClient {

  private static final RestTemplate restTemplate = new RestTemplate();
  private static final String rootUri = "http://localhost:8080/";

  static {
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
    restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

    FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
    restTemplate.getMessageConverters().add(formHttpMessageConverter);

    ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
    restTemplate.getMessageConverters().add(byteArrayHttpMessageConverter);
  }


  public static RestTemplate createRestTemplate() {
    return restTemplate;
  }
}
