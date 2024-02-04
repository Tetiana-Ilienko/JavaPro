package de.aittr.g_31_2_shop.domain.dto;

import de.aittr.g_31_2_shop.domain.interfaces.Cart;

import java.util.Objects;

public class CustomerDto {
    private int id;
    private String name;
    private CartDto cart;
    private int age;
    private String email;

    public CustomerDto(int id, String name, CartDto cart) {
        this.id = id;
        this.name = name;
        this.cart = cart;
    }

    public CustomerDto(int id, String name, CartDto cart, int age, String email) {
        this.id = id;
        this.name = name;
        this.cart = cart;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CartDto getCart() {
        return cart;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerDto that)) return false;
        return getId() == that.getId() && getAge() == that.getAge() && Objects.equals(getName(), that.getName()) && Objects.equals(getCart(), that.getCart()) && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCart(), getAge(), getEmail());
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cart=" + cart +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
