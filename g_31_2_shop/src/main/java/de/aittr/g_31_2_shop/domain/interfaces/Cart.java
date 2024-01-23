package de.aittr.g_31_2_shop.domain.interfaces;

import java.util.List;

public interface Cart {
    int getId();
    void setId(int id);

    List<Product> getProducts();
    void AddProduct(Product product);

    void deleteProduct(int productId);

    void clear();

    double getTotalPrice();

    double getAveragePrice();// возвращает среднюю стоимость товара

}
