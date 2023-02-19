package aruaru.user;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public record User(
        @JsonUnwrapped Id id,
        @JsonUnwrapped Name name,
        @JsonUnwrapped Password password,
        @JsonUnwrapped Email email) {

    public static User create(Integer id, String name, String password, String email) {
        return new User(new Id(id), new Name(name), new Password(password), new Email(email));
    }

    public static record Id(Integer id) {}
    public static record Name(String name) {}
    public static record Password(String password) {}
    public static record Email(String email) {}
}
