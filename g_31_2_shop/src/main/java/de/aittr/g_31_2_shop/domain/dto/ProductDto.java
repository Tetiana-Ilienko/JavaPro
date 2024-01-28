package de.aittr.g_31_2_shop.domain.dto;

import java.util.Objects;

public class ProductDto {
    private int id;
    private String name;
    private double price;

    public ProductDto(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDto that)) return false;
        return getId() == that.getId() && Double.compare(that.getPrice(), getPrice()) == 0 && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice());
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
