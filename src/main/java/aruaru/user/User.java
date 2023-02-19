package aruaru.user;

import aruaru.user.User.Id;
import aruaru.user.User.Name;

public record User(Id id, Name name) {

    public static record Id(int id) {

    }

    public static record Name(String name) {

    }

}
