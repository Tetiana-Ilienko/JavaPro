package app.services;

import app.domain.User;
import app.reposirories.UserRepository;

import java.util.List;

public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void addUser(String email, String password) {
        //   проверка параметров нв null и пустоту
        if (email.isEmpty()) {
            throw new IllegalArgumentException("E-mail введена не корректно");
        }

        if (!email.contains("@")) {
            throw new IllegalArgumentException("E-mail не содержит @ ");
        }

        //  проверить, не сущеcтвует ли уже в БД пользователь с таким email
        if (repository.findByEmail(email) != null) {
            throw new IllegalArgumentException("Такая e-mail уже существует");
        }

        repository.save(new User(email, password));

    }

    //  реализовать методы по получению одного и всех пользователей

    public User getUsersById(int id) {
        if (repository.findById(id) == null) {
            throw new IllegalArgumentException("Такого пользователя не существует");
        } else return repository.findById(id);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

}
