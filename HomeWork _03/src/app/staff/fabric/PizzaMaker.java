package app.staff.fabric;

public class PizzaMaker {

    private Deliverer deliverer;

    public Deliverer getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(Deliverer deliverer) {
        this.deliverer = deliverer;
    }

    public void work(){

        System.out.println("Пицца приготовлена  и передана на доставку!");
        deliverer.work();

    }
}
