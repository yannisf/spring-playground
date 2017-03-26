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
