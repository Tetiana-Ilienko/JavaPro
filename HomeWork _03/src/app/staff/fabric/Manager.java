package app.staff.fabric;

public class Manager {
    private PizzaMaker pizzaMaker;

    public PizzaMaker getPizzaMaker() {
        return pizzaMaker;
    }

    public void setPizzaMaker(PizzaMaker pizzaMaker) {
        this.pizzaMaker = pizzaMaker;
    }

    public void work(){
        System.out.println("Менеджер принял заказ и передал в изготовление!");
        pizzaMaker.work();
    }
}
