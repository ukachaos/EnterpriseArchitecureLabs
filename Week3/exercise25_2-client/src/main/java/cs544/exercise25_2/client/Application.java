package cs544.exercise25_2.client;

import cs544.exercise25_2.service.IGreeting;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StopWatch;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");
        IGreeting remoteService = context.getBean("greetingHttpInvokerProxy", IGreeting.class);

        StopWatch watch = new StopWatch();
        watch.start();
        int result = remoteService.calc('+', 10);
        watch.stop();
        System.out.println("Receiving result for (+10): " + result + ", took " + watch.getTotalTimeMillis() + "ms");

        watch.start();
        result = remoteService.calc('/', 2);
        watch.stop();
        System.out.println("Receiving result for (/2): " + result + ", took " + watch.getTotalTimeMillis() + "ms");

        watch.start();
        result = remoteService.calc('*', 5);
        watch.stop();
        System.out.println("Receiving result for (*5): " + result + ", took " + watch.getTotalTimeMillis() + "ms");

        watch.start();
        result = remoteService.calc('-', 1);
        watch.stop();
        System.out.println("Receiving result for (-1): " + result + ", took " + watch.getTotalTimeMillis() + "ms");

    }
}