package de.aittr.g_31_2_shop.services.jpa;

import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import de.aittr.g_31_2_shop.repositories.jpa.JpaProductRepository;
import de.aittr.g_31_2_shop.services.interfaces.ProductService;
import de.aittr.g_31_2_shop.services.mapping.ProductMappingService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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
        entity.setId(0);// передаем 0, чтобы не перезатирать данные, если клиет
        // пришлет продукт с уже существующим продуктом
        entity = repository.save(entity);
        return mappingService.mapProductEntityToDto(entity);
    }

    @Override
    public List<ProductDto> getAllActiveProducts() {
        return repository.findAll()
                .stream()
                .filter(JpaProduct::isActive)
                .map(p -> mappingService.mapProductEntityToDto(p))
                .toList();
    }

    @Override
    public ProductDto getActiveProductById(int id) {
        Product product = repository.findById(id).orElse(null);// данный метод
        // возвращает объект типа Optinal - поэтому надо добавлять метод orElse
        if (product != null && product.isActive()) {
            return mappingService.mapProductEntityToDto(product);
        }
        return null;
    }

    @Override
    public void update(ProductDto product) {
        repository.save(mappingService.mapDtoToJpaProduct(product));
        // что будет,если поменять id? - дать доступ только админу
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Product product = repository.findById(id).orElse(null);

        if (product != null && product.isActive()) {
            product.setActive(false);
        }
        //  вариант с ручным запросом
        // repository.deleteById(id);

    }

    @Override
    @jakarta.transaction.Transactional
    public void deleteByName(String name) {
        Product product = repository.findByName(name);

        if (product != null && product.isActive()) {
            product.setActive(false);
        }
        // repository.deleteByName(name);

    }

    @Override
    @Transactional
    public void restoreById(int id) {
        Product product = repository.findById(id).orElse(null);

        if (product != null && !product.isActive()) {
            product.setActive(true);
        }

       // repository.restoreById(id);

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
