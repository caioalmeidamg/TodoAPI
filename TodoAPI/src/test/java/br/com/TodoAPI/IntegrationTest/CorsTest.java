package br.com.TodoAPI.IntegrationTest;

import br.com.TodoAPI.IntegrationTest.Config.PostgresqlTestContainerConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CorsTest extends PostgresqlTestContainerConfig {

    private static final String ORIGIN_1 = "http://localhost:3000";
    private static final String ORIGIN_2 = "http://localhost:5000";
    private static final String ORIGIN_3 = "https://google.com.br";

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Test a CORS configuration allowing an origin")
    @Test
    public void testCorsConfigurationAllowedOrigin_1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/todos")
                        .header("Origin", ORIGIN_1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().exists("Access-Control-Allow-Origin"))
                .andExpect(MockMvcResultMatchers.header().string("Access-Control-Allow-Origin", ORIGIN_1));
    }

    @DisplayName("Test a CORS configuration allowing an origin")
    @Test
    public void testCorsConfigurationAllowedOrigin_2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/todos")
                        .header("Origin", ORIGIN_2))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().exists("Access-Control-Allow-Origin"))
                .andExpect(MockMvcResultMatchers.header().string("Access-Control-Allow-Origin", ORIGIN_2));
    }

    @DisplayName("Test a CORS configuration blocking an origin")
    @Test
    public void testCorsConfigurationBlockedOrigin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/todos")
                        .header("Origin", ORIGIN_3))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.header().doesNotExist("Access-Control-Allow-Origin"));
    }
}
