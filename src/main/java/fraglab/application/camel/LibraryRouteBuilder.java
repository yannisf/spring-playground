package fraglab.application.camel;

import org.apache.camel.builder.RouteBuilder;

public class LibraryRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("jms:fraglab.jms").to("bean:authorService?method=process");

    }

}
