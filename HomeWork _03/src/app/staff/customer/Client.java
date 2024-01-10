package app.staff.customer;

import app.staff.fabric.Deliverer;
import app.staff.fabric.Manager;
import app.staff.fabric.PizzaMaker;

public class Client {

    private Manager manager;


    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }


    public void order(){
        System.out.println("Клиент заказывает пиццу!");
        manager.work();

    }
}
