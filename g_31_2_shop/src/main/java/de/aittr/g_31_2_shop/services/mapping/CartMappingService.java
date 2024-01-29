package de.aittr.g_31_2_shop.services.mapping;

import de.aittr.g_31_2_shop.domain.dto.CartDto;
import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.domain.interfaces.Cart;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.domain.jdbc.CommonCart;
import de.aittr.g_31_2_shop.domain.jpa.JpaCart;
import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;

import java.util.List;

public class CartMappingService {

    ProductMappingService productMappingService = new ProductMappingService();

    public CartDto mapCartEntityToDto(Cart cart) {
        int id = cart.getId();
        List<Product> products = cart.getProducts();

        return new CartDto(id, products);
    }

    public CommonCart mapDtoToCommonCart(CartDto cart) {
        int id = cart.getId();
        List<Product> products = cart.getProducts();
        return new CommonCart(id, products);

    }

    public JpaCart mapDtoToJpaCart(CartDto cart) {
        int id = cart.getId();
        List<Product> products = cart.getProducts();
        List<JpaProduct> jpaProducts = products
                .stream()
                .map(p -> productMappingService.mapDtoToJpaProduct((ProductDto) p))
                .toList();
        return new JpaCart(id, jpaProducts);

    }

}
