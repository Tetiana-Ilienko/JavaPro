package de.aittr.g_31_2_shop.domain.dto;

import de.aittr.g_31_2_shop.domain.interfaces.Cart;

import java.util.Objects;

public class CustomerDto {
    private int id;
    private String name;
    private Cart cart;

    public CustomerDto(int id, String name, Cart cart) {
        this.id = id;
        this.name = name;
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Cart getCart() {
        return cart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerDto that)) return false;
        return getId() == that.getId() && Objects.equals(getName(), that.getName()) && Objects.equals(getCart(), that.getCart());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCart());
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cart=" + cart +
                '}';
    }
}
