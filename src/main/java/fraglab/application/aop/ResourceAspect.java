package fraglab.application.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.stream.Stream;

@Aspect
@Component
public class ResourceAspect {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceAspect.class);

    @Pointcut("execution(public * fraglab.application.library..*Resource.*(..))")
    private void publicResourceMethods() {}

    @Pointcut("execution(public * fraglab.application.library..*.*(..)) && @annotation(fraglab.application.annotations.Timed)")
    private void timed() {}

    @Before("fraglab.application.aop.ResourceAspect.publicResourceMethods()")
    public void doBefore(JoinPoint joinPoint) {
        LOG.info("{}", joinPoint.getStaticPart().toLongString());
        Stream.of(joinPoint.getArgs()).forEach( a -> LOG.info("{}: {}", a.getClass().getSimpleName(), a.toString()));
    }

    @Around("fraglab.application.aop.ResourceAspect.timed()")
    public Object timeExecution(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        LOG.info("Timing {}: {} ms", proceedingJoinPoint.getStaticPart().toShortString(), stopWatch.getTotalTimeMillis());
        return result;
    }


}
