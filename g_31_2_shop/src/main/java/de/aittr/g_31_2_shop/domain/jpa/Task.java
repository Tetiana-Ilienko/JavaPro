package de.aittr.g_31_2_shop.domain.jpa;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "task")
public class Task {
    @Column(name = "executed_at")
    private Timestamp executedAt;// специальный тип даты времени, который взаимодействует с БД

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    String description;

    public Task() {
        executedAt =new Timestamp(System.currentTimeMillis());
    }

    public Task(String description) {
        this.description = description;
        executedAt =new Timestamp(System.currentTimeMillis());
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return id == task.id && Objects.equals(executedAt, task.executedAt) && Objects.equals(getDescription(), task.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(executedAt, id, getDescription());
    }

    @Override
    public String toString() {
        return "Task{" +
                "executedAt=" + executedAt +
                ", id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
