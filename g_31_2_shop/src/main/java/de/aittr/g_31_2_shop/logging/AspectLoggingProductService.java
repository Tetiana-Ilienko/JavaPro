package de.aittr.g_31_2_shop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class AspectLoggingProductService {
    //TODO
    //    При помощи АОП сделать логирование всех методов сервиса продуктов (при помощи одного Pointcut).
    //    Для задания Pointcut использовать JpaProductService.*(..). В лог должно выводиться:
    //    А. Какой метод и с какими параметрами вызван.
    //    Б. Какой метод завершил работу.



    Logger logger = LoggerFactory.getLogger(AspectLoggingProductService.class);

    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.*(..))")
    public void productServiceMethod() {
    }

    @Before("productServiceMethod()")
    public void logBeforeProductServiceMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName(); // получила имя метода
        Object[] args = joinPoint.getArgs();// получила параметры метода

        StringBuilder builder = new StringBuilder("Вызван метод ");
        builder.append(methodName).append(" с параметрами: ");
        for (Object arg : args) {
            builder.append(arg).append(", ");
        }
        builder.setLength(builder.length() - 2);
        builder.append(".");
        logger.info(builder.toString());


    }


    @After("productServiceMethod()")
    public void logAfterProductServiceMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName(); // получила имя метода
        Object[] args = joinPoint.getArgs();// получила параметры метода

        StringBuilder builder = new StringBuilder("Mетод ");
        builder.append(methodName).append(" с параметрами: ");
        for (Object arg : args) {
            builder.append(arg).append(", ");
        }
        builder.setLength(builder.length() - 2);
        builder.append(" завершил работу.");
        logger.info(builder.toString());


    }

}
