package aruaru.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

import aruaru.TestClient;
import aruaru.common.JacksonPrettyPrinter;

@ActiveProfiles("develop")
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  RestTemplate restTemplate = TestClient.createRestTemplate();

  @Test
  void getUserById() throws Exception {
    mockMvc
        .perform(get("/users/1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(
            "{\"id\":1,\"name\":\"TestUser1\",\"password\":\"TestUser1Password\",\"email\":\"testuser1@email.com\",\"role\":\"システム管理者\"}"));
  }

  // usrIdは自動生成で登録されるので99にはならない
  @Test
  void post() throws URISyntaxException {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    User user = new User(99, "name", "password", "email", "一般ユーザー");

    RequestEntity<User> reqEntity = RequestEntity.post("/users").headers(headers).body(user);
    ResponseEntity<User> response = restTemplate.exchange(reqEntity, User.class);

    // 結果の取得
    System.out.println(response.getStatusCode());
    System.out.println(response.getHeaders());
    System.out.println(response.getBody());
  }

  @Test
  void put() throws URISyntaxException {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    User user = new User(1, "TestUser1", "TestUser1Password", "testuser1@email.com", "システム管理者");

    RequestEntity<User> reqEntity = RequestEntity.put("/users").headers(headers).body(user);
    ResponseEntity<User> response = restTemplate.exchange(reqEntity, User.class);

    // 結果の取得
    System.out.println(response.getStatusCode());
    System.out.println(response.getHeaders());
    System.out.println(response.getBody());
  }

  @Test
  void serialize() throws URISyntaxException, JsonProcessingException {
    User user = new User(99, "name", "password", "email", "一般ユーザー");

    ObjectMapper mapper = JsonMapper.builder().enable(SerializationFeature.INDENT_OUTPUT).build();
    mapper.setDefaultPrettyPrinter(new JacksonPrettyPrinter());

    String serializedUser = mapper.writeValueAsString(user);

    assertEquals("""
        {
          \"id\": 99,
          \"name\": \"name\",
          \"password\": \"password\",
          \"email\": \"email\",
          \"role\": \"一般ユーザー\"
        }""", serializedUser);
    System.out.println(serializedUser);
  }

  @Test
  void deserialize() throws URISyntaxException, JsonProcessingException {
    String json = "{\"id\":99,\"name\":\"name\",\"password\":\"password\",\"email\":\"email\",\"role\":\"一般ユーザー\"}";
    User deserializedUser = new ObjectMapper().readValue(json, User.class);
    User expectedUser = new User(99, "name", "password", "email", "一般ユーザー");

    assertEquals(expectedUser, deserializedUser);
    System.out.println(deserializedUser);
  }
}
