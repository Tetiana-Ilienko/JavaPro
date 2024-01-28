package de.aittr.g_31_2_shop.services.interfaces;

import de.aittr.g_31_2_shop.domain.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto save(ProductDto product);
    // метод вызывается контроллером, контроллер работает только с Dto,
    // поэтому мы меняем интерфейс, все будущие классы будут реализовывать данный интерфейс
    // и тоже работать с Dto

    List<ProductDto> getAllActiveProducts();// меняем во всех местах

    ProductDto getActiveProductById(int id);

    void update(ProductDto product);

    void deleteById(int id);

    void deleteByName(String name);

    void restoreById(int id);

    int getActiveProductCount();

    double getActiveTotalPrice();

    double getActiveProductAveragePrice();

}
