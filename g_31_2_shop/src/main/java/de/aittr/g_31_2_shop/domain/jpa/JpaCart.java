package de.aittr.g_31_2_shop.domain.jpa;

import de.aittr.g_31_2_shop.domain.interfaces.Cart;
import de.aittr.g_31_2_shop.domain.interfaces.Customer;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cart")
public class JpaCart implements Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private JpaCustomer customer;//  прописываем поле, чтобы было видно, кому принадлежит данная корзина

    @ManyToMany
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"), // указываем на таблицу с сущностью, с которой мы работаем
            inverseJoinColumns = @JoinColumn(name = "product_id")// связь ко второй таблице
            //!!!! не наоборот. Важно!
    )
    private List<JpaProduct> products = new ArrayList<>();

    public JpaCart() {
    }

    public JpaCart(int id, List<JpaProduct> products) {
        this.id = id;
        this.products = products;
    }



    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(JpaCustomer customer) {
        this.customer = customer;
    }



    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }


    public void setProducts(List<JpaProduct> products) {
        this.products = products;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {

    }



    @Override
    public void AddProduct(Product product) {

    }

    @Override
    public void deleteProduct(int productId) {

    }

    @Override
    public void clear() {

    }

    @Override
    public double getTotalPrice() {
        return 0;
    }

    @Override
    public double getAveragePrice() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JpaCart jpaCart)) return false;
        return getId() == jpaCart.getId() && Objects.equals(getCustomer(), jpaCart.getCustomer()) && Objects.equals(getProducts(), jpaCart.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomer(), getProducts());
    }

    @Override
    public String toString() {
        return "JpaCart{" +
                "id=" + id +
                ", customer=" + customer +
                ", products=" + products +
                '}';
    }
}
