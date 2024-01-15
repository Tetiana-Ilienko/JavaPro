package de.aittr.g_31_2_rest.domain;

import java.util.Objects;

public class Dog {

    private int id;
    private String breed;
    private String nickName;
    private int age;

    public Dog() {
    }

    public Dog(String breed, String nickName, int age) {
        this.breed = breed;
        this.nickName = nickName;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dog dog)) return false;
        return getId() == dog.getId() && getAge() == dog.getAge() && Objects.equals(getBreed(), dog.getBreed()) && Objects.equals(getNickName(), dog.getNickName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBreed(), getNickName(), getAge());
    }

    @Override
    public String toString() {
        return String.format("Dod ID: %d, breed: %s, nickName: %s, age: %d", id, breed, nickName, age);
    }
}
