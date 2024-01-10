package app.code;

import app.staff.customer.Client;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {

        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Client client = context.getBean(Client.class);
        client.order();
    }


}
