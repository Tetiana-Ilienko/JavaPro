package de.aittr.g_31_2_shop.services.mapping;

import de.aittr.g_31_2_shop.domain.dto.CartDto;
import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.domain.interfaces.Cart;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.domain.jdbc.CommonCart;
import de.aittr.g_31_2_shop.domain.jpa.JpaCart;
import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartMappingService {

    ProductMappingService productMappingService = new ProductMappingService();

    public CartMappingService(ProductMappingService productMappingService) {
        this.productMappingService = productMappingService;
    }

    public CartDto mapCartEntityToDto(Cart cart) {
        int id = (cart != null) ? cart.getId() : 0;

        List<ProductDto> products = cart.getProducts()
                .stream()
                .map(p -> productMappingService.mapProductEntityToDto(p))
                .toList();
        return new CartDto(id, products);
    }

    public CommonCart mapDtoToCommonCart(CartDto cart) {
        int id = cart.getId();

        List<Product> products = new ArrayList<>(cart.getProducts()
                .stream()
                .map(p -> productMappingService.mapDtoToCommonProduct(p))
                .toList());
        return new CommonCart(id, products);

    }

    public JpaCart mapDtoToJpaCart(CartDto cart) {
        int id = cart.getId();
        List<JpaProduct> products = cart.getProducts()
                .stream()
                .map(p -> productMappingService.mapDtoToJpaProduct(p))
                .toList();
        return new JpaCart(id, products);

    }

}
