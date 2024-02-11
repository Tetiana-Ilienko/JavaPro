package de.aittr.g_31_2_shop.scheduling;


import de.aittr.g_31_2_shop.domain.jpa.Task;
import de.aittr.g_31_2_shop.services.jpa.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
@EnableAsync
public class ScheduleExecutorHW {

    private Logger logger = LoggerFactory.getLogger(ScheduleExecutorHW.class);

    private TaskService taskService;

    public ScheduleExecutorHW(TaskService taskService) {
        this.taskService = taskService;
    }
    //TODO
    // Реализовать вывод в консоль каждые 30 секунд списка последних пяти выполненных задач.
    // Время выполнения предыдущей задачи не должно влиять на старт следующей.
    // Создавать новую задачу и логировать ничего не нужно.


    @Async
    @Scheduled(fixedRate = 30000) // Запуск каждые 30 секунд
    public void printLastFiveTasks() {
        System.out.println("Список последних пяти выполненных задач:");

        List<Task> lastFiveTasks = taskService.getLastFiveTasks();
        for (Task task : lastFiveTasks) {
            System.out.println(task);
        }
    }


    //TODO
    // Реализовать вывод в консоль последнего добавленного в БД товара.
    // Вывод должен производиться в 15 и 45 секунд каждой минуты.
    // Задача должна быть сохранена в БД.
    // Вывод в консоль должен быть осуществлён через логирование поля description созданной задачи.
    // Пример значения поля description - "Последний добавленный в БД продукт - Банан".

    @Scheduled(cron = "15,45 * * * * *")
    public void printLastAddedProduct() {

        taskService.createTask("Последний добавленный в БД продукт - "+ taskService.getLastAddedTask());


    }


}
