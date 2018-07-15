package fraglab.application.jms;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

public class PlaygroundJmsListenerContainerFactory extends DefaultJmsListenerContainerFactory {

    private PlaygroundMessageListenerContainer playgroundMessageListenerContainer = new PlaygroundMessageListenerContainer();

    @Override
    protected DefaultMessageListenerContainer createContainerInstance() {
        return playgroundMessageListenerContainer;
    }

    public void start() {
        playgroundMessageListenerContainer.start();
    }

    @EventListener
    public void contextRefreshed(ContextRefreshedEvent event) {
        System.out.println("**********************");
        System.out.println("contextRefreshed @ PlaygroundJmsListenerContainerFactory");
        System.out.println("**********************");
        this.start();
    }


}
