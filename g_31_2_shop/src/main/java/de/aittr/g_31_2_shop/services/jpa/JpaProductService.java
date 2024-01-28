package de.aittr.g_31_2_shop.services.jpa;

import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import de.aittr.g_31_2_shop.repositories.jpa.JpaProductRepository;
import de.aittr.g_31_2_shop.services.interfaces.ProductService;
import de.aittr.g_31_2_shop.services.mapping.ProductMappingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class JpaProductService implements ProductService {
    private JpaProductRepository repository;
    private ProductMappingService mappingService;

    public JpaProductService(JpaProductRepository repository, ProductMappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }

    @Override
    public ProductDto save(ProductDto dto) {
        JpaProduct entity = mappingService.mapDtoToJpaProduct(dto);
        entity.setId(0);
        entity = repository.save(entity);
        return mappingService.mapProductEntityToDto(entity);
    }

    @Override
    public List<ProductDto> getAllActiveProducts() {
        return repository.findAll()
                .stream()
                .map(p -> mappingService.mapProductEntityToDto(p))
                .toList();
    }

    @Override
    public ProductDto getActiveProductById(int id) {
        Optional<JpaProduct> product = repository.findById(id);
        if (product.get().isActive()) {
            return mappingService.mapProductEntityToDto(product.get());
        }else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void update(ProductDto product) {
       repository.save(mappingService.mapDtoToJpaProduct(product));
       //TODO что будет,если поменять id?
    }

    @Override
    public void deleteById(int id) {
       repository.deleteById(id);

    }

    @Override
    public void deleteByName(String name) {
        repository.deleteByName(name);

    }

    @Override
    public void restoreById(int id) {
        repository.restoreById(id);

    }

    @Override
    public int getActiveProductCount() {
        return 0;
    }

    @Override
    public double getActiveTotalPrice() {
        return 0;
    }

    @Override
    public double getActiveProductAveragePrice() {
        return 0;
    }
}
