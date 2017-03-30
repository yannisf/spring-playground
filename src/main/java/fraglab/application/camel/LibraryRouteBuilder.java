package fraglab.application.camel;

import org.apache.camel.builder.RouteBuilder;

public class LibraryRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("jms:fraglab.jms")
                .to("bean:authorService?method=process1")
                .log(simple("${body}").toString())
                .to("direct:title");

        from("direct:title")
                .to("bean:authorService?method=process2")
                .log(simple("${body}").toString())
                .to("direct:file");

        from("direct:file")
                .to("file://c:/local?autoCreate=true&fileName=title.txt");

    }

}
