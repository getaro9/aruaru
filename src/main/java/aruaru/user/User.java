package aruaru.user;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public record User(
        @JsonUnwrapped Id id,
        @JsonUnwrapped Name name) {

    public static record Id(Integer id) {}
    public static record Name(String name) {}
}
