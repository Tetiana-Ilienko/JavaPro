package de.aittr.g_31_2_shop.domain.jdbc;

import de.aittr.g_31_2_shop.domain.interfaces.Product;

import java.util.Objects;

public class CommonProduct implements Product {

    private int id;
    private boolean isActive;
    private String name;
    private double price;

    public CommonProduct() {
        isActive = true;
    }

    public CommonProduct(int id, boolean isActive, String name, double price) {
        this.id = id;
        this.isActive = isActive;
        this.name = name;
        this.price = price;
    }

    @Override
    public void setActive(boolean active) {
        isActive = active;
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
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setId(int id) {
        this.id = id;

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommonProduct that)) return false;
        return getId() == that.getId() && isActive() == that.isActive() && Double.compare(that.getPrice(), getPrice()) == 0 && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), isActive(), getName(), getPrice());
    }

    @Override
    public String toString() {
        return String.format("ID - %d, наименование - %s, цена - %.2f, активен - %s",
                id, name, price, isActive ? "да" : "нет");
    }
}
