package visumap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {

    @InjectMocks
    private Application app = new Application();

    @Test(expected = IllegalArgumentException.class)
    public void testNoArgs() throws Exception {
        app.main(null);
    }
}