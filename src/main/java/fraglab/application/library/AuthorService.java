package fraglab.application.library;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

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
            return session.createObjectMessage(book.getTitle());
        });

    }

}
