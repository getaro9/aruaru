package aruaru.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aruaru.user.User.Id;
import aruaru.user.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable Integer id) {
        User user = userRepository.selectById(new Id(id));
        return user;
    }

    @GetMapping(value = "/register")
    public User get3() {
        User user= User.create(null, "name", "password", "email", "一般ユーザー");
        User reUser = userRepository.save(user);
        return reUser;
    }

}
