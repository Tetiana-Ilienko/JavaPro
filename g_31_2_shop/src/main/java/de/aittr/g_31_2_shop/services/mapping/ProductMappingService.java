package de.aittr.g_31_2_shop.services.mapping;

import de.aittr.g_31_2_shop.domain.jdbc.CommonProduct;
import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import org.springframework.stereotype.Service;

@Service
public class ProductMappingService {
    public ProductDto mapProductEntityToDto(Product product){
        // достаем все необходимые данные, которые приходять на вход (Product product)
        int id = product.getId();
        String name = product.getName();
        double price = product.getPrice();
        // возвращаем новый объект Dto, которому передаем все полученные параметры
        return new ProductDto(id,name,price);
    }

    // метод, который делает обратную конвертацию
    public CommonProduct mapDtoToCommonProduct(ProductDto product){
        int id = product.getId();
        String name = product.getName();
        double price = product.getPrice();
        return new CommonProduct(id, true,name,price);
        // по ТО - каждый новый продукт - активный, поэтому мы сразу предаем в конструкторе isActive:true

    }

    public JpaProduct mapDtoToJpaProduct(ProductDto product){
        int id = product.getId();
        String name = product.getName();
        double price = product.getPrice();
        return new JpaProduct(id, name,price,true);

    }

}
