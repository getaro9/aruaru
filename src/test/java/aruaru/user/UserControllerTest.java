package aruaru.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("develop")
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUserById() throws Exception {
        mockMvc
          .perform(get("/users/1"))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().json("{\"id\":1,\"name\":\"TestUser1\",\"password\":\"TestUser1Password\",\"email\":\"testuser1@email.com\",\"role\":\"システム管理者\"}"));
    }

}
