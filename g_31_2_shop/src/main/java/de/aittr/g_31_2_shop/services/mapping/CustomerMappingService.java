package de.aittr.g_31_2_shop.services.mapping;

import de.aittr.g_31_2_shop.domain.dto.CartDto;
import de.aittr.g_31_2_shop.domain.dto.CustomerDto;
import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.domain.interfaces.Cart;
import de.aittr.g_31_2_shop.domain.interfaces.Customer;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.domain.jdbc.CommonCustomer;
import de.aittr.g_31_2_shop.domain.jdbc.CommonProduct;
import de.aittr.g_31_2_shop.domain.jpa.JpaCart;
import de.aittr.g_31_2_shop.domain.jpa.JpaCustomer;
import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import org.springframework.stereotype.Service;

@Service
public class CustomerMappingService {
    private CartMappingService cartMappingService;

    public CustomerMappingService(CartMappingService cartMappingService) {
        this.cartMappingService = cartMappingService;
    }

    public CustomerDto mapCustomerEntityToDto(Customer customer){

        int id = customer.getId();
        String name = customer.getName();
        CartDto cartDto = cartMappingService.mapCartEntityToDto(customer.getCard());

        return new CustomerDto(id,name, cartDto);
    }


    public CommonCustomer mapDtoToCommonCustomer(CustomerDto customer){
        int id = customer.getId();
        String name = customer.getName();
        Cart cart = cartMappingService.mapDtoToCommonCart(customer.getCart());
        return new CommonCustomer(id, true,name,cart);


    }

    public JpaCustomer mapDtoToJpaCustomer(CustomerDto customer){
        int id = customer.getId();
        String name = customer.getName();
        JpaCart cart = cartMappingService.mapDtoToJpaCart(customer.getCart());
        return new JpaCustomer(id, true,name, cart);

    }

}
