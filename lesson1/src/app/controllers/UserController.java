package app.controllers;

import app.services.UserService;

import java.util.Scanner;

public class UserController {

    private Scanner scanner;
    private UserService service;

    public UserController(Scanner scanner, UserService service) {
        this.scanner = scanner;
        this.service = service;
    }

    public void addUser() {
        System.out.println("Введите E-mail");
        String email = scanner.nextLine();

        System.out.println("Введите пароль");
        String password = scanner.nextLine();

        try {
            service.addUser(email, password);
            System.out.println("Пользователь успешно добавлен");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

    }

    //  реализовать метод получения одного пользователя из БД
    //  (идентификатор нужно запросить у клиента через консоль)
    public void getUserById() {
        System.out.println("Введите id пользователя");
        int id = scanner.nextInt();
        try {
            System.out.println(service.getUsersById(id));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }


    // TODO реализовать метод получения всех пользователей
    // TODO Оба эти метода должны просто выводить результат в консоль
    public void getAllUsers(){
        System.out.println(service.getAll());
    }
}
