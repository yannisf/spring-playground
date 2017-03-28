## JMS
A typical JMS programming flow concerns:

1. Setting up a broker (e.g. ActiveMQ)
### Sender (producer)
2. Creatings a connection factory for this broker
3. Getting a connection from the connection factory
4. Getting a session from the connection
5. Creating a destination (queue or topic)
6. Creating a message producer from the session using the destination
7. Sending the message
### Receiver (consumer)
8. Creatings a connection factory for this broker
3. Getting a connection from the connection factory
4. Getting a session from the connection
5. Creating a destination (queue or topic)
6. Creating a message consumer from the session using the destination
7. Receive the message 


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
