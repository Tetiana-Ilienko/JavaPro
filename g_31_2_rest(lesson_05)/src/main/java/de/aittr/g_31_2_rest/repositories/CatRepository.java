package de.aittr.g_31_2_rest.repositories;

import de.aittr.g_31_2_rest.domain.Cat;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CatRepository implements CrudRepository<Cat> {

    private final String DB_DRIVER_PATH = "com.mysql.cj.jdbc.Driver";
    private final String DB_ADDRESS = "jdbc:mysql://127.0.0.1:3306/";
    private final String DB_NAME = "31_2_cats";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "zhjckfd";

    private final String ID = "id";
    private final String COLOR = "color";
    private final String WEIGHT = "weight";

    private Map<Integer, Cat> cats = new HashMap<>();
    private int currentId;

//    // создаем конструктор с тремя котами (имитируем работу с базой данных), при реальной
//    // работе - пустой конструктор создается автоматически при компиляции
//    public CatRepository() {
//        save(new Cat("Black", 2.34));
//        save(new Cat("White", 3.45));
//        save(new Cat("Gray", 4.56));
//    }

    //    подключаем базу данных
    private Connection getConnection() {
        try {
            Class.forName(DB_DRIVER_PATH);
            String dbUrl = String.format("%s%s?user=%s&password=%s",
                    DB_ADDRESS, DB_NAME, DB_USERNAME, DB_PASSWORD);
            return DriverManager.getConnection(dbUrl);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Cat save(Cat cat) {
        cat.setId(++currentId);
        cats.put(currentId, cat);
        return cat;
    }

    @Override
    public List<Cat> getAll() {
        // return new ArrayList<Cat>(cats.values());
        try (Connection connection = getConnection()) {
            String query = "select * from 31_2_cats.cat;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Cat> cats = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                String color = resultSet.getString(COLOR);
                double weight = resultSet.getDouble(WEIGHT);
                Cat cat = new Cat(id, color, weight);
                cats.add(cat);
            }
            return cats;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Cat getById(int id) {
        try (Connection connection = getConnection()) {
            String query = String.format("SELECT * FROM 31_2_cats.cat where id=%d", id);
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            Cat cat = null;
            while (resultSet.next()) {
                String color = resultSet.getString(COLOR);
                double weight = resultSet.getDouble(WEIGHT);
                cat = new Cat(id,color, weight);
            }
            return cat;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Cat obj) {

    }

    @Override
    public void deleteBiId(int id) {

    }
}
