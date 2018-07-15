# JMS

A typical JMS programming flow concerns:

1. Setting up a broker (e.g. ActiveMQ)

## Sender (producer)

2. Creating a connection factory for this broker
3. Getting a connection from the connection factory
4. Getting a session from the connection
5. Creating a destination (queue or topic)
6. Creating a message producer from the session using the destination
7. Sending the message

## Receiver (consumer)
8. Creating a connection factory for this broker
3. Getting a connection from the connection factory
4. Getting a session from the connection
5. Creating a destination (queue or topic)
6. Creating a message consumer from the session using the destination
7. Receive the message 

---
A JMS listener container factory needs to be created, as to create the the JMS listener container (the container where the JMS listener will exist). Typically the SimpleJmsListenerContainerFactory, but a more complete option is the DefaultJmsListenerContainerFactory.

Within a JMS listener container, exists the DefaultMessageListenerContainer that is the core of the JMS listener container.


## Testing

### Integration tests
Integration tests use spring-test. Just include the dependency and annotate the test with:

```
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
```

A challenge is to provide the context configuration properly if it resided into WEB-INF. This can be achieved in two ways.

#### File resource

    @ContextConfiguration("file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")

#### Classpath resource
Add in `pom.xml`:
```
<testResources>
    <testResource>
        <directory>src/main/webapp/WEB-INF</directory>
    </testResource>
</testResources>
```

and then in the test:
    
    @ContextConfiguration("classpath:dispatcher-servlet.xml")
