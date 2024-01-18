package de.aittr.g_31_2_rest.repositories;

import de.aittr.g_31_2_rest.domain.Parrot;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ParrotRepository implements CrudRepository<Parrot> {
    private final String DB_DRIVER_PATH = "com.mysql.cj.jdbc.Driver";
    private final String DB_ADDRESS = "jdbc:mysql://127.0.0.1:3306/";
    private final String DB_NAME = "31_2_parrots";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "zhjckfd";

    private final String ID = "id";
    private final String COLOR = "color";
    private final String WEIGHT = "weight";

    // Метод, который подключается к БД и возвращает объект подключения
    private Connection getConnection() {
        try {
            Class.forName(DB_DRIVER_PATH);
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
    public Parrot save(Parrot obj) {
        // TODO
        return null;
    }

    @Override
    public List<Parrot> getAll() {

        // блок try с ресурсами, то что надо после соединения закрыть
        // можно использовать только методы, которые реализуют интерфейс
        // Closeable.  иначе использовать блок finally
        try (Connection connection = getConnection()) { // получили соединение с БД

            String query = "select * from parrot;"; // отправляем запрос в базу данных
            Statement statement = connection.createStatement(); // соединились с БД
            ResultSet resultSet = statement.executeQuery(query); // получаем ответ
            List<Parrot> parrots = new ArrayList<>();
            // метод next()  возвращает boolean
            while (resultSet.next()) {
                // считываем id
//               int id = resultSet.getInt(1); вариант 1 - передаем номер колонки
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
//        finally {
//
//        }
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

            //TODO

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
