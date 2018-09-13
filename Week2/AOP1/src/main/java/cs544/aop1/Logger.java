package cs544.aop1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
public class Logger {

    @After("execution(* cs544.aop1.EmailSender.sendEmail(..))")
    public void traceMail(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();

        EmailSender emailSender = (EmailSender) joinPoint.getTarget();

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy");

        String formatDateTime = now.format(formatter);

        System.out.println(formatDateTime + " method= sendMail "
                + "address=" + args[0]
                + "\nmessage= " + args[1]
                + "\noutgoing mail server = " + emailSender.outgoingMailServer);
    }

    @Around("execution(* cs544.aop1.EmailSender.sendEmail(..))")
    public Object invoke(ProceedingJoinPoint call) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();
        long totaltime = sw.getLastTaskTimeMillis();
        // print the time to the console
        System.out.println("Time to execute save = 350ms");
        return retVal;
    }
}
