package aruaru.user;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import aruaru.user.User.UserDeserializer;
import lombok.With;

@With
@JsonDeserialize(using = UserDeserializer.class)
public record User(
    @JsonUnwrapped Id id,
    @JsonUnwrapped Name name,
    @JsonUnwrapped Password password,
    @JsonUnwrapped Email email,
    @JsonUnwrapped Role role) {

  public User(Integer id, String name, String password, String email, String role) {
    this(new Id(id), new Name(name), new Password(password), new Email(email), new Role(role));
  }

  public static record Id(Integer id) {
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public Id {}
  }
  public static record Name(String name) {
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public Name {}
  }
  public static record Password(String password) {
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public Password {}
  }
  public static record Email(String email) {
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public Email {}
  }
  public static record Role(String role) {
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public Role {}
  }

  /**
   * Jacksonでのデシリアライズのため。
   */
  public static class UserDeserializer extends StdDeserializer<User> {

    public UserDeserializer() {
      this(null);
    }

    public UserDeserializer(Class<?> vc) {
      super(vc);
    }

    @Override
    public User deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {

      JsonNode userNode = jp.getCodec().readTree(jp);
      User user = new User(
          userNode.get("id").intValue(),
          userNode.get("name").textValue(),
          userNode.get("password").textValue(),
          userNode.get("email").textValue(),
          userNode.get("role").textValue());

      return user;
    }
  }
}
