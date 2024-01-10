package app.code;

import app.staff.customer.Client;
import app.staff.fabric.Deliverer;
import app.staff.fabric.Manager;
import app.staff.fabric.PizzaMaker;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {

//        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        Client client = context.getBean(Client.class);
//        client.order();


//        ******************************
        Deliverer deliverer = new Deliverer();

        PizzaMaker pizzaMaker = new PizzaMaker();
        pizzaMaker.setDeliverer(deliverer);

        Manager manager = new Manager();
        manager.setPizzaMaker(pizzaMaker);

        Client client = new Client();
        client.setManager(manager);

        client.order();

    }


}
