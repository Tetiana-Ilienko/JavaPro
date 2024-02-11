package de.aittr.g_31_2_shop.domain.jpa;

import de.aittr.g_31_2_shop.domain.interfaces.Cart;
import de.aittr.g_31_2_shop.domain.interfaces.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    @NotNull
    @NotEmpty
    private String name;

    // привязана к другой таблице - показываем связь с другой таблицей
    @OneToOne(mappedBy = "customer") //  наименование поля, через которое осуществляется связь
    private JpaCart cart;

    @Column(name = "age")
    @Min(5)
    @Max(110)
    private int age;
    @Column(name = "e-mail")
    @Email
    @NotNull
    private String email;

    private static Logger logger = LoggerFactory.getLogger(JpaCustomer.class);

    public JpaCustomer() {
        logger.info("Запрошен пустой конструктор для JpaCustomer");
    }

    public JpaCustomer(int id, boolean isActive, String name, JpaCart cart) {
        logger.info("Запрошен конструктор для JpaProduct с параметрами: id: {}, isActive: {}, name: {}, cart: {} ",
                id, isActive, name, cart);
        this.id = id;
        this.isActive = isActive;
        this.name = name;
        this.cart = cart;
    }

    public JpaCustomer(int id, boolean isActive, String name, JpaCart cart, int age, String email) {
        this.id = id;
        this.isActive = isActive;
        this.name = name;
        this.cart = cart;
        this.age = age;
        this.email = email;
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
        } catch (Exception e) {
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
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JpaCustomer that)) return false;
        return getId() == that.getId() && isActive() == that.isActive() && getAge() == that.getAge() && Objects.equals(getName(), that.getName()) && Objects.equals(cart, that.cart) && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), isActive(), getName(), cart, getAge(), getEmail());
    }

    @Override
    public String toString() {
        return "JpaCustomer{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", name='" + name + '\'' +
                ", cart=" + cart +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
