package aruaru.user;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public record User(
        @JsonUnwrapped Id id,
        @JsonUnwrapped Name name) {

    public static User create(Integer id, String name) {
        return new User(new Id(id), new Name(name));
    }

    public static record Id(Integer id) {}
    public static record Name(String name) {}
}
