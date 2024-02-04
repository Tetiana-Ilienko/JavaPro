package de.aittr.g_31_2_shop.domain.jdbc;

import de.aittr.g_31_2_shop.domain.interfaces.Cart;
import de.aittr.g_31_2_shop.domain.interfaces.Customer;

import java.util.Objects;

public class CommonCustomer implements Customer {
    private  int id;
    private boolean isActive;
    private String name;
    private Cart cart;
    private int age;
    private String email;

    public CommonCustomer() {
        this.isActive = true;
    }

    public CommonCustomer(int id, boolean isActive, String name, Cart cart) {
        this.id = id;
        this.isActive = isActive;
        this.name = name;
        this.cart = cart;
    }

    public CommonCustomer(int id, boolean isActive, String name, Cart cart, int age, String email) {
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
        this.name=name;

    }

    @Override
    public Cart getCard() {
        return cart;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setCart(Cart cart) {
        this.cart=cart;
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
        if (!(o instanceof CommonCustomer that)) return false;
        return getId() == that.getId() && isActive() == that.isActive() && getAge() == that.getAge() && Objects.equals(getName(), that.getName()) && Objects.equals(cart, that.cart) && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), isActive(), getName(), cart, getAge(), getEmail());
    }

    @Override
    public String toString() {
        return "CommonCustomer{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", name='" + name + '\'' +
                ", cart=" + cart +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
