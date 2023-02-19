package aruaru.user;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping(value= "/get")
    public User register(HttpServletResponse response,HttpServletRequest request, Model model) {

        return new User(new User.Id(1), new User.Name("user"));
    }


}
