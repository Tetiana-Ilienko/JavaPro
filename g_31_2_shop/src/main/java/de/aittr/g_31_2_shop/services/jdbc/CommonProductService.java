package de.aittr.g_31_2_shop.services.jdbc;

import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.domain.jdbc.CommonProduct;
import de.aittr.g_31_2_shop.enams.Status;
import de.aittr.g_31_2_shop.repositories.interfaces.ProductRepository;
import de.aittr.g_31_2_shop.services.interfaces.ProductService;
import de.aittr.g_31_2_shop.services.mapping.ProductMappingService;

import java.util.List;

//@Service
public class CommonProductService implements ProductService {
    private ProductRepository repository;
    private ProductMappingService mappingService;

    public CommonProductService(ProductRepository repository, ProductMappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }
    // service  - это третий слой, который с одно стороны работает с Контроллером (Dto),
    // а с другой с Репозиторием (CommonProduct)
    // значит в сервисе должна проискодить конвертация

    // контроллер передает на сохранение, репозиторий - записывает, присваеивает id и отдает обратно
    @Override
    public ProductDto save(ProductDto product) {
        // на вход мы принимает Dto
        //конвентируем его в CommonProduct
        CommonProduct commonProduct = mappingService.mapDtoToCommonProduct(product);
        // передаем сконвертируемый продукт в репозиторий
        Product saveProduct = repository.save(commonProduct);
        // метод save возвращает нам Product которому уже присвоен id, нам надо произвести обратную конвертацию
        return mappingService.mapProductEntityToDto(saveProduct);

    }

    @Override
    public List<ProductDto> getAllActiveProducts() {
        // получаем список продуктов
        return repository.getAll()
                .stream()
                // каждый элемент надо сконвентировать в ProductDto, используем метод мар
                .map(p -> mappingService.mapProductEntityToDto(p))
                // собираем обратно в список
                .toList();
    }

    @Override
    public ProductDto getActiveProductById(int id) {
        return null;
    }

    @Override
    public void update(ProductDto product) {
        repository.update(mappingService.mapDtoToCommonProduct(product));
    }

    @Override
    public void deleteById(int id) {
        //repository.deleteById(id);
        repository.changeStatusById(id, Status.DELETE);
    }


    @Override
    public void deleteByName(String name) {

    }

    @Override
    public void restoreById(int id) {
        repository.changeStatusById(id, Status.RESTORE);
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
