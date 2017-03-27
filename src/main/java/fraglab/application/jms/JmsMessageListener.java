package fraglab.application.jms;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class JmsMessageListener implements MessageListener {

    public void onMessage(Message message) {
        System.out.println("Message received");
        TextMessage tm = (TextMessage) message;
        try {
            String text = tm.getText();
            System.out.println(String.format("Text is [%s]", text));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
