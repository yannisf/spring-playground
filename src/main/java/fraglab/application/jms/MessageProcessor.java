package fraglab.application.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessor {

//    @JmsListener(destination = "fraglab.jms")
    public void process(String text) {
        System.out.println(String.format("Text is [%s]", text));
    }

}
