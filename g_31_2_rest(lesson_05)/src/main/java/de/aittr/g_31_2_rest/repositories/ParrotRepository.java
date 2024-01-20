package de.aittr.g_31_2_rest.repositories;

import de.aittr.g_31_2_rest.domain.Parrot;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Repository
public class ParrotRepository implements CrudRepository<Parrot> {

    //  задаем константы, которые служат для обеспечения доступа к базе данных
    private final String DB_DRIVER_PATH = "com.mysql.cj.jdbc.Driver";// путь к драйверу БД (эту инфу можно найти в инете)
    private final String DB_ADDRESS = "jdbc:mysql://127.0.0.1:3306/";// путь к БД можно написать localhost:3306
    // при изменении БД, меняются только константы, кот остается неизменным
    private final String DB_NAME = "31_2_parrots";// имя БД
    private final String DB_USERNAME = "root";// логин, по умолчанию имя пользователя root (корневой пользователь),
    // в реальности эти данные индивидуальны
    private final String DB_PASSWORD = "zhjckfd";// пароль

    private final String ID = "id";
    private final String COLOR = "color";
    private final String WEIGHT = "weight";

    // Метод, который подключается к БД и возвращает объект подключения
    private Connection getConnection() {
        try {
            Class.forName(DB_DRIVER_PATH); // драйвер базы подколючается к нашему приложению
            // jdbc:mysql://localhost:3306/31_2_parrots?user=root&password=zhjckfd
            // Составляет URL для подключения к базе данных.
            String dbUrl = String.format("%s%s?user=%s&password=%s", DB_ADDRESS, DB_NAME, DB_USERNAME, DB_PASSWORD);

            return DriverManager.getConnection(dbUrl);// Устанавливает соединение с базой данных, используя созданный URL.

//            В случае возникновения ошибки (например, если драйвер не найден или не удается установить соединение),
//            метод выбрасывает исключение RuntimeException с сообщением об ошибке.
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Parrot save(Parrot parrot) {
        try (Connection connection = getConnection()) {
            String query = String.format(Locale.US,"INSERT INTO `31_2_parrots`.`parrot` (`color`, `weight`) VALUES ('%s', '%.2f');",
                    parrot.getColor(), parrot.getWeight());
            boolean resultSet = connection.createStatement().execute(query);
            if (!resultSet) {
                return parrot;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



    @Override
    public List<Parrot> getAll() {

        // блок try с ресурсами, то что надо после соединения закрыть
        // не ллюбой ресурс можно использовать в блоке try с ресурсами:
        // можно использовать только методы, которые реализуют интерфейс Closeable/AutoCloseable
        // иначе использовать блок finally.
        // блок try с ресурсами позволяет нам не заботиться о том, чтоб внешний ресурс, к которому мы получили доступ
        // был закрыт - он делает это автоматически

        //
        try (Connection connection = getConnection()) { // получили соединение с БД

            String query = "SELECT * FROM 31_2_parrots.parrot;"; // отправляем запрос в базу данных
            Statement statement = connection.createStatement(); // соединились с БД c помощью объест
            // интерфейса Statement
            ResultSet resultSet = statement.executeQuery(query); // получаем ответ
            List<Parrot> parrots = new ArrayList<>();
            // метод next()  возвращает boolean
            while (resultSet.next()) {
                // считываем id
                //int id = resultSet.getInt(1); вариант 1 - передаем номер колонки
                int id = resultSet.getInt(ID); // вариант 2 - передать название (очень предпочтительно)
                String color = resultSet.getString(COLOR);
                double weight = resultSet.getDouble(WEIGHT);
                Parrot parrot = new Parrot(id, color, weight);
                parrots.add(parrot);
            }
            return parrots;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        ручное закрытие ресурсов
//        finally {
//        if(!getConnection().isClosed()){
//          connection.close();
//        }    }


    }

    @Override
    public Parrot getById(int id) {

        try (Connection connection = getConnection()) {
            String query = String.format("select * from parrot where id = %d;", id);

            ResultSet resultSet = connection.createStatement().executeQuery(query);
            Parrot parrot = null;

            while (resultSet.next()) {
                String color = resultSet.getString(COLOR);
                double weight = resultSet.getDouble(WEIGHT);
                parrot = new Parrot(id, color, weight);
            }
            return parrot;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Parrot obj) {
        try (Connection connection = getConnection()) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteBiId(int id) {
        try (Connection connection = getConnection()) {
            String query = String.format("DELETE FROM `31_2_parrots`.`parrot` WHERE (`id` = %d);",id);
            boolean resultSet = connection.createStatement().execute(query);
            if (resultSet) {
                throw new IllegalArgumentException("Попугай с таким id  в базе не найден!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
