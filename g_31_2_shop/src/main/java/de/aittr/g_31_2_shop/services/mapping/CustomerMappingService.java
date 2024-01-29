package de.aittr.g_31_2_shop.services.mapping;

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

    public CustomerDto mapCustomerEntityToDto(Customer customer){

        int id = customer.getId();
        String name = customer.getName();
        Cart cart = customer.getCard();

        return new CustomerDto(id,name,cart);
    }


    public CommonCustomer mapDtoToCommonCustomer(CustomerDto customer){
        int id = customer.getId();
        String name = customer.getName();
        Cart cart = customer.getCart();
        return new CommonCustomer(id, true,name,cart);


    }

    public JpaCustomer mapDtoToJpaCustomer(CustomerDto customer){
        int id = customer.getId();
        String name = customer.getName();
        Cart cart = customer.getCart();
        return new JpaCustomer(id, true,name,(JpaCart) cart);

    }

}
