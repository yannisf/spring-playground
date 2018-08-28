package fraglab.application.it;

import fraglab.application.it.embedded.EmbeddedJetty;
import fraglab.application.it.embedded.EmbeddedServer;
import fraglab.application.it.embedded.EmbeddedTomcat;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class AbstractRestIntegrationTest {

    private static final EmbeddedServer EMBEDDED_SERVER = new EmbeddedJetty();
    protected RestTemplate restTemplate = new RestTemplate();

    @BeforeClass
    public void startServer() throws Exception {
        EMBEDDED_SERVER.start();
    }

    @AfterClass
    public void stopServer() throws Exception {
        EMBEDDED_SERVER.stop();
    }

}
