package fraglab.application.library;

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

    public String process() {
        return "processed";
    }

    public void publish(Book book) {
        System.out.println("Publishing");
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                System.out.println("Sending message");
                TextMessage message = session.createTextMessage(book.getTitle());
                return message;
            }
        });

    }

}
