package fraglab.application.library;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private JmsTemplate jmsTemplate;

    public String process(Exchange exchange) {
        System.out.println("Processing..." + exchange.toString());
        return "processed";
    }

    public void publish(Book book) {
        System.out.println("Publishing");
        jmsTemplate.send(session -> {
            System.out.println("Sending message");
            return session.createObjectMessage(book);
        });

    }

    public String process1(Book book) {
        System.out.println("Received " + book);
        return book.getTitle();
    }

    public String process2(String body) {
        return String.format("(process2: %s)", body);
    }


}
