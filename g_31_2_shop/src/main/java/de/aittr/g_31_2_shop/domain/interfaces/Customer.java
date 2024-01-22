package de.aittr.g_31_2_shop.domain.interfaces;

public interface Customer {
    int getId();
    boolean isActive();
    String getName();
    Cart getCard();
    void  setId(int id);
    void  setCartId(int id);
}
