package de.aittr.g_31_2_shop.domain.jpa;

import de.aittr.g_31_2_shop.domain.interfaces.Cart;
import de.aittr.g_31_2_shop.domain.interfaces.Customer;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "customer")
public class JpaCustomer implements Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "name")
    private String name;

    // привязана к другой таблице - показываем связь с другой таблицей
    @OneToOne(mappedBy = "customer") //  наименование поля, через которое осуществляется связь
    private JpaCart cart;

    public JpaCustomer() {
    }

    public JpaCustomer(int id, boolean isActive, String name, JpaCart cart) {
        this.id = id;
        this.isActive = isActive;
        this.name = name;
        this.cart = cart;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setCart(Cart cart) {
        try {
            this.cart = (JpaCart) cart;
        }catch (Exception e){
            throw new IllegalArgumentException(" В сеттер JpaCustomer передан несовместимый тип корзины");
        }

    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Cart getCard() {
        return cart;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JpaCustomer that)) return false;
        return getId() == that.getId() && isActive() == that.isActive() && Objects.equals(getName(), that.getName()) && Objects.equals(cart, that.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), isActive(), getName(), cart);
    }

    @Override
    public String toString() {
        return "JpaCustomer{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", name='" + name + '\'' +
                ", cart=" + cart +
                '}';
    }
}
