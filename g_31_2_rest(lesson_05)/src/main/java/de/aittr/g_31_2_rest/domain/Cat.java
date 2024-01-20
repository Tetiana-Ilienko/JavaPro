package de.aittr.g_31_2_rest.domain;

import java.util.Objects;

public class Cat {
    private int id;
    private String color;
    private double weight;

    public Cat() {
    }

    public Cat(int id, String color, double weight) {
        this.id = id;
        this.color = color;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cat cat)) return false;
        return getId() == cat.getId() && Double.compare(cat.getWeight(), getWeight()) == 0 && Objects.equals(getColor(), cat.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getColor(), getWeight());
    }

    @Override
    public String toString() {
        return String.format("Cat: ID - %d, color - %s, weight - %3.f", id, color, weight);
    }
}
