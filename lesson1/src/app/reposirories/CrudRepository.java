package app.reposirories;

import java.util.List;

// CRUD - Create reade Update Delete
public interface CrudRepository<T> {

    void save(T entity); // сохранить сущность

    T findById(int id); // найти по id
    List<T> findAll(); // вывести всю БД

    void update(T entity);// изменить / обновить БД

    void deleteById(int id);// удалить по id
}
