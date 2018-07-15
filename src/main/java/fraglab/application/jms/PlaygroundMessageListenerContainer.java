package fraglab.application.jms;

import org.springframework.jms.JmsException;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

public class PlaygroundMessageListenerContainer extends DefaultMessageListenerContainer {

    @Override
    public void start() throws JmsException {
        super.start();
    }

    @Override
    public boolean isAutoStartup() {
        return false;
    }
}

