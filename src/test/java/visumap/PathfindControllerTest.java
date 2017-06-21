package visumap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PathfindControllerTest {

    private final String API_VERSION = "0.1v";

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void randomPointsStatusOk() throws Exception {
        mockMvc.perform(get("/"+ API_VERSION + "/randompoints/"))
                .andExpect(status().isOk());
    }

    @Test
    public void shortestPathStatusOk() throws Exception {
        mockMvc.perform(get("/"+ API_VERSION + "/shortest//"))
                .andExpect(status().isOk());
    }


    @Test
    public void responseTypeApplicationJson() throws Exception {
        mockMvc.perform(get("/" + API_VERSION + "/shortest"))
                .andReturn().getResponse().getContentAsString().contains("[");
    }

    @Test
    public void mainApiWorkOk() throws Exception {
        mockMvc.perform(get("/" + API_VERSION + "/randompoints/"))
                .andReturn().getResponse().getContentAsString().contains("[");
    }
}