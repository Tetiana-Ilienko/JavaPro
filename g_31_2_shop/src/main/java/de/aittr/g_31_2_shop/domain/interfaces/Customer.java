package de.aittr.g_31_2_shop.domain.interfaces;

public interface Customer {
    int getId();
    boolean isActive();
    void setActive(boolean isActive);
    String getName();
    void setName(String name);
    Cart getCard();
    void  setId(int id);
    void  setCart(Cart cart);
}
