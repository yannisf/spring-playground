package fraglab.application.it.embedded;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class EmbeddedTomcat implements EmbeddedServer {

    private Tomcat tomcat;
    private static final int PORT = 8080;
    private static final String CONTEXT_PATH = "/";
    private static final String WEBAPP_DIR = "src/main/webapp/";
    private static final String CLASSES_DIR = "target/classes";

    @Override
    public void start() throws Exception {
        tomcat = new Tomcat();
        tomcat.setPort(PORT);

        String webAppDirPath = new File(WEBAPP_DIR).getAbsolutePath();
        StandardContext context = (StandardContext) tomcat.addWebapp(CONTEXT_PATH, webAppDirPath);

        String webInfClassesPath = new File(CLASSES_DIR).getAbsolutePath();
        WebResourceRoot resources = new StandardRoot(context);
        DirResourceSet resourceSet = new DirResourceSet(resources, "/WEB-INF/classes", webInfClassesPath, "/");
        resources.addPreResources(resourceSet);
        context.setResources(resources);

        tomcat.start();
    }

    @Override
    public void stop() throws Exception {
        tomcat.stop();
    }

}
