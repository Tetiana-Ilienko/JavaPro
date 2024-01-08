package app.reposirories;

import app.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepositoryMap implements UserRepository{ // реализует интерфейс

    private Map<Integer, User> users = new HashMap<>(); // храним БД в Мар
    private int currentId;


    @Override
    public void save(User entity) {
        entity.setId(++currentId);
        users.put(currentId, entity);

    }

    @Override
    public User findById(int id) {
        //  домашнее задание
        return users.get(id);
    }

    @Override
    public List<User> findAll() {
        // домашнее задание
        return new ArrayList<>(users.values());
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
}
