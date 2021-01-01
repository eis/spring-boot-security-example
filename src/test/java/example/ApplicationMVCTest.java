package example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationMVCTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loginShouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Username")))
                .andExpect(content().string(containsString("Password")));
    }

    @Test
    public void loginShouldFailWithoutCSRF() throws Exception {
        this.mockMvc.perform(post("/login")
                .param("username", "user")
                .param("password", "password"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void loginShouldSucceed() throws Exception {
        this.mockMvc.perform(post("/login")
                .param("username", "user")
                .param("password", "password")
                .with(csrf()))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "/"));
    }
}
