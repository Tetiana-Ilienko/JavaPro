package de.aittr.g_31_2_rest.repositories;

import java.util.List;

public interface CrudRepository<T> { //<T>  параметризуем интерфейс для гибкости

    T save(T obj); // Create

    List<T> getAll(); // Read

    T getById(int id); // Read

    void update(T obj);// Update

    void deleteBiId(int id);// Delete


}
