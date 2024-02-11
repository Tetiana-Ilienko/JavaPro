package de.aittr.g_31_2_shop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLoggingAllService {
    //TODO
    //    При помощи АОП сделать логирование всех сервисов (при помощи одного Pointcut).
    //    В лог должно выводиться:
    //    А. Какой метод какого класса и с какими параметрами вызван.
    //    Б. Какой метод какого класса завершил работу.
    //    В. Какой метод какого класса успешно вернул результат.
    //    Г. Какой метод какого класса вызвал ошибку.

    Logger logger = LoggerFactory.getLogger(AspectLoggingAllService.class);


    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa..*(..))")
    public void serviceMethod() {
    }

    @Before("serviceMethod()")
    public void logBeforeProductServiceMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();

        StringBuilder builder = new StringBuilder("Вызван метод ");
        builder.append(methodName).append(" с параметрами: ");
        for (Object arg : args) {
            builder.append(arg).append(", ");
        }
        builder.setLength(builder.length() - 2);
        builder.append(".");
        logger.info(builder.toString());


    }

    @After("serviceMethod()")
    public void logAfterProductServiceMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();

        StringBuilder builder = new StringBuilder("Mетод ");
        builder.append(methodName).append(" с параметрами: ");
        for (Object arg : args) {
            builder.append(arg).append(", ");
        }
        builder.setLength(builder.length() - 2);
        builder.append(" завершил работу.");
        logger.info(builder.toString());


    }

    @AfterReturning(
            pointcut = "serviceMethod()",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();

        StringBuilder builder = new StringBuilder("Mетод ");
        builder.append(methodName).append(" с параметрами: ");
        for (Object arg : args) {
            builder.append(arg).append(", ");
        }
        builder.setLength(builder.length() - 2);
        builder.append(" завершил работу и успешно вернул результат: ").append(result);
        logger.info(builder.toString());

    }


    @AfterThrowing(
            pointcut = "serviceMethod()",
            throwing = "e"
    )
    public void throwingExceptionWhileReturning(JoinPoint joinPoint, Exception e) {
        String methodName = joinPoint.getSignature().getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();

        StringBuilder builder = new StringBuilder("Mетод ");
        builder.append(methodName).append(" с параметрами: ");
        for (Object arg : args) {
            builder.append(arg).append(", ");
        }
        builder.setLength(builder.length() - 2);
        builder.append(" выбросил ошибку: ").append(e.getMessage());
        logger.info(builder.toString());


    }


}
