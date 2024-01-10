package app.staff.customer;

import app.staff.fabric.Deliverer;
import app.staff.fabric.Manager;
import app.staff.fabric.PizzaMaker;

public class Client {

    private Manager manager;
    private PizzaMaker pizzaMaker;
    private Deliverer deliverer;

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public PizzaMaker getPizzaMaker() {
        return pizzaMaker;
    }

    public void setPizzaMaker(PizzaMaker pizzaMaker) {
        this.pizzaMaker = pizzaMaker;
    }

    public Deliverer getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(Deliverer deliverer) {
        this.deliverer = deliverer;
    }

    public void order(){
        System.out.println("Клиент заказывает пиццу!");
        manager.work();
        pizzaMaker.work();
        deliverer.work();
    }
}
