package de.aittr.g_31_2_shop.repositories;

import de.aittr.g_31_2_shop.domain.interfaces.Customer;
import de.aittr.g_31_2_shop.repositories.interfaces.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import static de.aittr.g_31_2_shop.repositories.DBConnector.getConnection;

@Repository
public class CommonCustomerRepository implements CustomerRepository {
    private final String CUSTOMER_ID = "id";
    private final String CART_ID = "id";

    private final String NAME = "name";

//    Реализовать сохранение в БД нового покупателя. Для каждого покупателя в БД сразу же должна сохраняться его корзина.
    @Override
    public Customer save(Customer customer) {
        try(Connection connection = getConnection()) {
            String query = String.format("INSERT INTO `31_2_shop`.`customer` (`name`, `is_active`)" +
                    " VALUES ('%s', '1');", customer.getName());
            connection.createStatement().execute(query);
            query = "select id from 31_2_shop.customer order by id desc limit 1;"; // получаем id покупателя
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            resultSet.next();
            int id = resultSet.getInt(CUSTOMER_ID);
            query = String.format("INSERT INTO `31_2_shop`.`cart` (`customer_id`) VALUES ('%d');", id); // по id
            // покупателя создаем id корзины
            connection.createStatement().execute(query);

            query = String.format("SELECT * FROM 31_2_shop.cart where customer_id=%d",id);
            resultSet = connection.createStatement().executeQuery(query); // получаем id корзины
            resultSet.next();

            int car_id = resultSet.getInt(CART_ID);



            customer.setId(id);
            customer.setCartId(car_id);
            return customer;


        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public Customer getById(int id) {
        return null;
    }

    @Override
    public void update(int id) {

    }

    @Override
    public void deleteById(int id) {

    }
}
