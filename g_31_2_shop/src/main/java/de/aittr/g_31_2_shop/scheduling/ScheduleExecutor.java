package de.aittr.g_31_2_shop.scheduling;

import de.aittr.g_31_2_shop.domain.jpa.Task;
import de.aittr.g_31_2_shop.services.jpa.TaskService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.ScheduledFuture;

@Component
@EnableScheduling
@EnableAsync // включаем а-синхронное выполнение задач
public class ScheduleExecutor {

    private TaskService taskService;
    private static Logger logger = LoggerFactory.getLogger(ScheduledFuture.class);

    public ScheduleExecutor(TaskService taskService) {
        this.taskService = taskService;
    }

//    @Scheduled(fixedDelay = 5000)
//    public void fixedDeleteTask(){
//        taskService.createTask("Fixed delay task");
//    }

//    @Scheduled(fixedDelay = 5000)
//    public void fixedDeleteTask(){
//        taskService.createTask("Fixed delay task 3000ms");
//        try {
//            Thread.sleep(3000);
//        }catch (InterruptedException e){
//            throw  new RuntimeException(e);
//        }
//    }



//    @Scheduled(fixedRate = 5000)
//    public void fixedRateTask(){
//        taskService.createTask("Fixed rate task 3000ms");
//        try {
//            Thread.sleep(3000);
//        }catch (InterruptedException e){
//            throw  new RuntimeException(e);
//        }
//    }


//    @Scheduled(fixedRate = 5000)
//    public void fixedRateTask(){
//        taskService.createTask("Fixed rate task 7000ms");
//        try {
//            Thread.sleep(7000);
//        }catch (InterruptedException e){
//            throw  new RuntimeException(e);
//        }
//    }
//

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



//    @Scheduled(fixedDelay = 5000, initialDelay = 20000)
//    public void initialDelayTask(){
//        taskService.createTask("Initial delete  task ");
//
//    }


    /** Другой формат записания интервала
    2 hours ->72000000 milliseconds -> PT2H*/
//    @Scheduled(fixedDelayString = "PT3S")
//    public void anotherDelayFormatTask(){
//        taskService.createTask("Another delay format  task ");
//
//    }
    /** Cron -выражение*/

//    @Scheduled(cron = "10,30 * * * * *")
//    public void cronExpressionTask(){
//        taskService.createTask("Cron expression  task ");
//
//    }

    /** Мы хотим запускать задачу, после того, как кто-то запросил все продукты из базы данных*/
//    public static void scheduleAndExecuteTask(Task task){
//        TaskScheduler scheduler = new DefaultManagedTaskScheduler();
//        scheduler.schedule(
//                ()-> logger.info(task.getDescription()),
//                new CronTrigger("0,10,20,30,40,50 * * * * *")
//        );
//
//    }

    /** Мы хотим запускать задачу, после того, как кто-то запросил все продукты из базы данных*/
    public static void scheduleAndExecuteTask(Task task){
        TaskScheduler scheduler = new DefaultManagedTaskScheduler();
        Instant instant = Instant.now().plusSeconds(30);
        scheduler.schedule(
                ()-> logger.info(task.getDescription()),
               instant
        );

    }


}
