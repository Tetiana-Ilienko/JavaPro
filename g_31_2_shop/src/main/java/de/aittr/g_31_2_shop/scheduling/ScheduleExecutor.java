package de.aittr.g_31_2_shop.scheduling;

import de.aittr.g_31_2_shop.domain.jpa.Task;
import de.aittr.g_31_2_shop.services.jpa.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.ScheduledFuture;

//@Component // для того, чтобы Спринг  создал бин
//@EnableScheduling// включается планировщик задач
//@EnableAsync // включаем а-синхронное выполнение задач
public class ScheduleExecutor {
    private static Logger logger = LoggerFactory.getLogger(ScheduledFuture.class);


    private TaskService taskService;

    public ScheduleExecutor(TaskService taskService) {
        this.taskService = taskService;
    }

    /** Запускаем задачу по расписанию */
//    @Scheduled(fixedDelay = 5000)
//    public void fixedDeleteTask(){
//        taskService.createTask("Fixed delay task");
//    }

    /** Имитируем задачу, которая выполняется какое-то время
     * Интервал межу задачами составляет = время задержки + время выполнения задачи
     * интервал указывается между концом и началом следующей задачи*/
//    @Scheduled(fixedDelay = 5000)
//    public void fixedDeleteTask(){
//        taskService.createTask("Fixed delay task 3000ms");
//        try {
//            Thread.sleep(3000);
//        }catch (InterruptedException e){
//            throw  new RuntimeException(e);
//        }
//    } Интервал межу задачами составляет = время задержки + время выполнения задачи

    /** Задача выполняется дольше, чем сам интервал между задачами*/
//    @Scheduled(fixedDelay = 5000)
//    public void fixedDeleteTask(){
//        taskService.createTask("Fixed delay task 7000ms");
//        try {
//            Thread.sleep(7000);
//        }catch (InterruptedException e){
//            throw  new RuntimeException(e);
//        }
//    }

    /** Атрибут fixedRate - работает по другому принципу
     * Интервал межу задачами составляет = время задержки
     * интервал указывается между началом и началом*/
//    @Scheduled(fixedRate = 5000)
//    public void fixedRateTask(){
//        taskService.createTask("Fixed rate task 3000ms");
//        try {
//            Thread.sleep(3000);
//        }catch (InterruptedException e){
//            throw  new RuntimeException(e);
//        }
//    }

    /** Задача выполняется дольше, чем интервал между задачами
     * если задачи запущены в одном потоке, то следующая задача начнется только после окончания предыдущей
     */
//    @Scheduled(fixedRate = 5000)
//    public void fixedRateTask(){
//        taskService.createTask("Fixed rate task 7000ms");
//        try {
//            Thread.sleep(7000);
//        }catch (InterruptedException e){
//            throw  new RuntimeException(e);
//        }
//    }


    /** Задача выполняется дольше, чем интервал между задачами
     * если задачи запущены в параллельных  потоках, то следующая задача начнется только после окончания предыдущей
     */
//    @Async // данный метод запустится в многопоточном режиме
//    @Scheduled(fixedRate = 5000)
//    public void fixedRateTask(){
//        taskService.createTask("Fixed rate async task 7000ms");
//        try {
//            Thread.sleep(7000);
//        }catch (InterruptedException e){
//            throw  new RuntimeException(e);
//        }
//    }


    /**Задачи запускаются каждые 5 сек, но с отложенным стартом*/
    //    @Scheduled(fixedDelay = 5000, initialDelay = 20000)
//    public void initialDelayTask(){
//        taskService.createTask("Initial delete  task ");
//    }


    /** Другой формат написания интервала
     2 hours ->72000000 milliseconds -> PT2H*/
//    @Scheduled(fixedDelayString = "PT3S")
//    public void anotherDelayFormatTask(){
//        taskService.createTask("Another delay format  task ");
//    }


    /** Cron -выражение*/
//    @Scheduled(cron = "10,30 * * * * *")
//    public void cronExpressionTask(){
//        taskService.createTask("Cron expression  task ");
//    }

    // 55 * * * * * -> каждую минуту в момент времени 55 секунд
    // 0 10,20 * * * * -> каждый час в 10 минут и в 20 минут
    // 0 15 9-17 * * MON-FRI -> по будням с 9 до 17 в 15 минут каждого часа


    /** Мы хотим запускать задачу, после того, как кто-то запросил все продукты из базы данных используем Trigger
     * задачу внедряем в нужный метод*/
//    public static void scheduleAndExecuteTask(Task task){
//        TaskScheduler scheduler = new DefaultManagedTaskScheduler();

    // TaskScheduler - это интерфейс, его реализует класс DefaultManagedTaskScheduler
    // мы получили объект, который умеет запускать определенные задачи по расписанию
    // обращаемся к объекту и передаем ему и саму задачу и расписание, по которому эта задача запускается

//        scheduler.schedule(
//                ()-> logger.info(task.getDescription()),// передаем саму задачу
//                new CronTrigger("0,10,20,30,40,50 * * * * *")// объект тригера
//        );
//
//    }

    /**
     * Мы хотим запускать задачу, после того, как кто-то запросил все продукты из базы данных используем Instant
      при этом задача выполниться разово, в тот момент, который мы определим
     например, мы хотим выполнить нашу задачу через 30 сек, как будут запрошены все продукты*/
//    public static void scheduleAndExecuteTask(Task task) {
//        TaskScheduler scheduler = new DefaultManagedTaskScheduler();
//
//        // создаем объект инстант, который будет соответствовать заданному моменту времени
//        Instant instant = Instant.now().plusSeconds(30);
//        scheduler.schedule(
//                () -> logger.info(task.getDescription()),
//                instant
//        );
//
//    }


}
