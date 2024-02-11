package de.aittr.g_31_2_shop.services.jpa;

import de.aittr.g_31_2_shop.domain.jpa.Task;
import de.aittr.g_31_2_shop.repositories.jpa.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository repository;

    /** Создаем логгер, для того, чтобы информацию о выполненной задаче записывать в лог
     */
    private Logger logger = LoggerFactory.getLogger(TaskService.class);

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    /** Метод, который логирует задачу и сохраняет ее в БД, время задачи назначается автоматически
      */
    public void createTask(String description) {
        logger.info(description);
        Task task = new Task(description);
        repository.save(task);

    }

    public List<Task> getLastFiveTasks(){
        return repository.findTop5ByOrderByExecutedAtDesc();

    }

    public Task getLastAddedTask() {
        return repository.findFirstByOrderByExecutedAtDesc();


    }
}
