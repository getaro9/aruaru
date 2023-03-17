package aruaru.user;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import aruaru.user.User.Id;
import aruaru.user.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Value("${aruaru.web.root-uri}")
    @Autowired
    String rootUri;

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable Integer id) {
        User user = userRepository.selectById(new Id(id));
        return user;
    }

    @PostMapping
    public ResponseEntity<User> register(@RequestBody User user) {
      User reUser = userRepository.save(user);
      URI location = UriComponentsBuilder.fromUriString(rootUri + "/users/" + reUser.id().id()).build().toUri();
      return ResponseEntity.created(location).body(reUser);
    }
}
