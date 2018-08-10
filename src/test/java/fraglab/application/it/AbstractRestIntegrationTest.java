package fraglab.application.it;

import fraglab.application.it.embedded.EmbeddedJetty;
import fraglab.application.it.embedded.EmbeddedServer;
import fraglab.application.it.embedded.EmbeddedTomcat;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractRestIntegrationTest {

    private static final EmbeddedServer EMBEDDED_SERVER = new EmbeddedTomcat();
    protected RestTemplate restTemplate = new RestTemplate();

    @BeforeClass
    public static void startServer() throws Exception {
        EMBEDDED_SERVER.start();
    }

    @AfterClass
    public static void stopServer() throws Exception {
        EMBEDDED_SERVER.stop();
    }

}
