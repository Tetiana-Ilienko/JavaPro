package de.aittr.g_31_2_shop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// этот класс работает по принципу Inversion of control
// для этого используються аннотации

//@Aspect
//@Component // для создания бина
public class AspectLogging {
    private Logger logger = LoggerFactory.getLogger(AspectLogging.class);


    // определяем Аdvice -
    // JoinPoint -  место, куда будет внедряться код
    // Pointcut - совокупность всех джоинпоинтов, куда будет внедряться адвайс
    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.getAllActiveProducts(..))")
    // прописывает простой Pointcut только к одному методу
    public void getProducts() {
    }// метод служит лиш для объявления Pointcut

    @Before("getProducts()")// (адвайс) указывется название метода, к которому  Pointcut привязан
    public void beforeGetProducts() {
        logger.info("Вызван метод getAllActiveProducts");// пишется сам внедренный код
    }


    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.restoreById(int))")
    public void restoreProduct() {
    }
    // мы можем настроить Pointcut таким образом, что наш авайс внедрялся даже в несколько классов
    // для этого и нужет JoinPoint

    @After("restoreProduct()")// адвайс
    public void afterRestoreProduct(JoinPoint joinPoint) {
        // JoinPoint - объект, который содержит информацию о точке присоединения,
        // его мы передаем в метод
        /**   joinPoint.getSignature().getDeclaringTypeName(); */ // когда внедряется в
        // несколько классов, таким образом мы можем определить в какой метод какого класса вызван
        // когда отработал именно этот адвайс
        Object[] args = joinPoint.getArgs();// получаем инфу об id, который пришел в метод
        logger.info("Вызван метод restoreById  с идентификатором {}.", args[0]);

    }

    // рассматриваем ситуацию с несколькими методами для логирования. Pointcut может быть привязан не только к нескольким
    // методам, но и к нескольким классам
    // как пройтись по массиву и вывести все параметры

    public void testAdvice(JoinPoint joinPoint) {
        // (String name, int id) -> ["petya", 6] Вызван метод с параметрами, petya, 6
        // (int id) -> [7] Вызван метод с параметрами 7
        // () -> []
        // (double price, String name, Cat cat, Product product)
        Object[] args = joinPoint.getArgs();
        // как мы можем залогировать все параметры, которые к нам пришли (ситуация с неодним методом)
        StringBuilder builder = new StringBuilder("Вызван метод с параметрами: ");
        for (Object arg : args) {
            builder.append(arg).append(", ");
        }
        // Вызван метод с параметрами: 7, Petya, 4.56, cat,
        builder.setLength(builder.length() - 2);
        builder.append(".");
        logger.info(builder.toString());
    }


    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.getActiveProductById(int))")
    public void getActiveProductById() {
    }

    // пишем адвайс, который отрабатывает после того, как метод возвращает успешный результат
    @AfterReturning(
            pointcut = "getActiveProductById()",// атрибут, которым мы привязываем Pointcut
            returning = "result" // арибут, который помогает вытянуть сам объект (переменная
            // в которую запишеться результат работы метода )
    )
    public void afterReturningProduct(JoinPoint joinPoint, Object result) {
        Object id = joinPoint.getArgs()[0];
        logger.info("Метод getActiveProductById вызван с параметром {} " +
                " и успешно вернул результат: {}.", id, result);
    }

    // пишем адвайс, который отрабатывает после того, как метод выбрасывет ошибку

    @AfterThrowing(
            pointcut = "getActiveProductById()",
            throwing = "e"//переменная, куда выброситься результат ошибки
    )
    public void throwingExceptionWhileReturningProduct(JoinPoint joinPoint, Exception e) {
        Object id = joinPoint.getArgs()[0];
        logger.warn("Метод getActiveProductById вызван с параметром {}" +
                "и выбросил ошибку: {}", id, e.getMessage());
    }


    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.getActiveProductCount(..))")
    public void getActiveProductCount() {
    }

    @Around("getActiveProductCount()")
    public Object aroundGettingProductCount(ProceedingJoinPoint joinPoint) {// ProceedingJoinPoint наследник Joinpoint,
        // у которого расширен функционал

        //  код, который будет выполняться до метода (засекает время)
        logger.info("Вызван метод getActiveProductCount. ");
        long start = System.currentTimeMillis();


        // выполняется сам код метод getActiveProductCount
        // здесь мы так же можем написать код, который будет выполняться вместо нашего метода
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        // код, который будет выполняться после метода (вычисляет сколько времени заняло
        // выпонение метода)
        long time = System.currentTimeMillis() - start;
        logger.info("Метод getActiveProductCount отработал за {} миллисекунд с результатом {}.", time, result);

        // можно подменить результат работы метода, не вмешиваясь в сам код работы метода
        // logger.info("Подменяем действительный результат на своё значение - 500.");
        //        return 500;
        // В логе будет записан истинный результат и подмена, но клиент увидет только 500


        return result;


    }


}
