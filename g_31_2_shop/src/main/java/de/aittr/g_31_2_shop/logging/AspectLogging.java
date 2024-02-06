package de.aittr.g_31_2_shop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// этот класс работает по принципу Inversion of control
// для этого используються аннотации

@Aspect
@Component // для создания бина
public class AspectLogging {
    private Logger logger = LoggerFactory.getLogger(AspectLogging.class);


    // определяем Аdvice -
    // JoinPoint -  место, куда будет внедряться код
    // Pointcut - совокупность всех джоинпоинтов, куда будет внедряться адвайс
    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.getAllActiveProducts(..))")
    // прописывает простой Pointcut только к одному методу
    public void getProducts() {
    }// метод служит лиш для объявления Pointcut

    @Before("getProducts()")// указывется название метода, к которому  Pointcut привязан
    public void beforeGetProducts() {
        logger.info("Вызван метод getAllActiveProducts");// пишется сам внедренный код
    }



    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.restoreById(int))")
    public void restoreProduct() {
    }
    // мы можем настроить Pointcut таким образом, что наш авайс внедрялся даже в несколько классов
    // для этого и нужет JoinPoint

    @After("restoreProduct()")
    public void afterRestoreProduct(JoinPoint joinPoint) {
        // JoinPoint - объект, который содержит информацию о точке присоединения,
        // его мы передаем в метод
     /**   joinPoint.getSignature().getDeclaringTypeName(); */ // когда внедряется в
        // несколько классов, таким образом мы можем определить в какой метод какого класса вызван
        // когда отработал именно этот адвайс
        Object[] args = joinPoint.getArgs();// получаем инфу об id, который пришел в метод
        logger.info("Вызван метод restoreById  с идентификатором {}.", args[0]);

    }

    // рассматриваем ситуацию с несколькими методами для логирования
    // как пройтись по массиву и вывести все параметры

    public  void testAdvice(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        // как мы можем залогировать все параметры, которые к нам пришли (ситуация с неодним методом)
        StringBuilder builder = new StringBuilder("Вызван метод с параметрами: ");
        for (Object arg : args){
            builder.append(arg).append(", ");
        }
        builder.setLength(builder.length() - 2);
        builder.append(".");
        logger.info(builder.toString());
    }
}
