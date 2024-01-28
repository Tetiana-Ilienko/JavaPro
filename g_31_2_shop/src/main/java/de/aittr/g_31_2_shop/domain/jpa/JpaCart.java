package de.aittr.g_31_2_shop.domain.jpa;

import de.aittr.g_31_2_shop.domain.interfaces.Cart;
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

    private List<Product> products = new ArrayList<>();

    public JpaCart() {
    }

    public JpaCart(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public List<Product> getProducts() {
        return products;
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
        return getId() == jpaCart.getId() && Objects.equals(getProducts(), jpaCart.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProducts());
    }

    @Override
    public String toString() {
        return "JpaCart{" +
                "id=" + id +
                ", products=" + products +
                '}';
    }
}
