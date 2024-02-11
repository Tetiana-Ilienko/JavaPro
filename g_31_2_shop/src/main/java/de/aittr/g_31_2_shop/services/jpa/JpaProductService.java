package de.aittr.g_31_2_shop.services.jpa;

import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import de.aittr.g_31_2_shop.domain.jpa.Task;
import de.aittr.g_31_2_shop.exeption_handling.exceptions.*;
import de.aittr.g_31_2_shop.repositories.jpa.JpaProductRepository;
import de.aittr.g_31_2_shop.scheduling.ScheduleExecutor;
import de.aittr.g_31_2_shop.services.interfaces.ProductService;
import de.aittr.g_31_2_shop.services.mapping.ProductMappingService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

@Service
public class JpaProductService implements ProductService {

    private JpaProductRepository repository;
    private ProductMappingService mappingService;


    /**
     * Библиотека  org.apache.logging.log4j
     */
   // private Logger logger = LogManager.getLogger(JpaProductService.class);// создание объекта логера


    /**
     * Библиотека org.slf4j (Создаем объект логера используя другую библиотеку) тут обращаемся к дркгому
     * классу LoggerFactory. У этой библиотеки нет метода log(), поэтому для логирования используем
     * второй вариант
     */
    //private Logger logger = LoggerFactory.getLogger(JpaProductService.class);
    public JpaProductService(JpaProductRepository repository, ProductMappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }

    @Override
    public ProductDto save(ProductDto dto) {
        try {
            JpaProduct entity = mappingService.mapDtoToJpaProduct(dto);
            entity.setId(0);// передаем 0, чтобы не перезатирать данные, если клиет
            // пришлет продукт с уже существующим продуктом
            entity = repository.save(entity);
            return mappingService.mapProductEntityToDto(entity);
        } catch (Exception e) {
            throw new ProductValidationException("Incorrect values of product fields ", e);
        }

    }

    @Override
    public List<ProductDto> getAllActiveProducts() {
        Task task = new Task("Method getAllActiveProducts called");
        ScheduleExecutor.scheduleAndExecuteTask(task);
        /**здесь будет JoinPoint, сюда будет внедряться вспомогательный код (АОП, логирование) Before */
        return repository.findAll()
                .stream()
                .filter(JpaProduct::isActive)
                .map(p -> mappingService.mapProductEntityToDto(p))
                .toList();
    }

    @Override
    public ProductDto getActiveProductById(int id) {
        /** Первый способ (логирование)*/
        // логирование на уровень инфо
       // logger.log(Level.INFO, String.format("Запрошен продукт с идентификатором %d", id));
        /** Внимание! тут пишем только в учебных целях - так в реальных проектах не пишут*/
//        logger.log(Level.WARN, String.format("Запрошен продукт с идентификатором %d",id));
//        logger.log(Level.ERROR, String.format("Запрошен продукт с идентификатором %d",id));

        /** Второй способ (логирование) имеем возможность не указывать уровень, а вызывать соответствующий метод*/
//        logger.info(String.format("Запрошен продукт с идентификатором %d", id));
//        logger.warn(String.format("Запрошен продукт с идентификатором %d", id));
//        logger.error(String.format("Запрошен продукт с идентификатором %d", id));

        /** Когда используется АОП - нам нет необходимости засорять наш код дополнительной логикой*/


        Product product = repository.findById(id).orElse(null);// данный метод
        // возвращает объект типа Optinal - поэтому надо добавлять метод orElse
        if (product != null && product.isActive()) {
            return mappingService.mapProductEntityToDto(product);
        }
        /** Выбрасываем ошибку  */
        // throw new FirstTestException("Продукт с указанным идентификатором отсутствует в базе данных.");
        // throw new SecondTestException("Продукт с указанным идентификатором отсутствует в базе данных.");
        throw new ThirdTestException("Продукт с указанным идентификатором отсутствует в базе данных.");
    }

    @Override
    public void update(ProductDto product) {
        try {
            repository.save(mappingService.mapDtoToJpaProduct(product));
            // что будет,если поменять id? - дать доступ только админу
        } catch (ProductUpdateException e) {
            throw new ProductUpdateException("Ошибка! Ввели не корректные данные для обновления.");
        }

    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Product product = repository.findById(id).orElse(null);

        if (product != null && product.isActive()) {
            product.setActive(false);
        } else {
            throw new ProductDeletionException("Продукт с указанным идентификатором отсутствует в базе данных" +
                    "или был уже удален.");
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
        } else {
            throw new ProductDeletionException("Продукт с указанным именем отсутствует в базе данных" +
                    "или был уже удален.");
        }
        // repository.deleteByName(name);

    }

    @Override
    @Transactional
    public void restoreById(int id) {
        Product product = repository.findById(id).orElse(null);

        if (product != null && !product.isActive()) {
            product.setActive(true);
        } else {
            throw new ProductUpdateException("Ошибка! Продукт с указанным идентификатором не найден или уже активен.");
        }

        /**здесь будет JoinPoint, сюда будет внедряться вспомогательный код (АОП, логирование) After */

        // repository.restoreById(id);

    }

    // вернуть общее количество активных продуктов в БД
    @Override
    public int getActiveProductCount() {
        int count = (int) repository.findAll()
                .stream()
                .filter(p -> p.isActive())
                .count();
        if (count == 0) {
            throw new NoActiveProductsException("Нет активных продуктов!");
        } else return count;
    }

    @Override
    public double getActiveTotalPrice() {
        Double totalPrice = repository.findAll()
                .stream()
                .filter(p -> p.isActive())
                .mapToDouble(p -> p.getPrice())
                .sum();
        if (totalPrice == 0) {
            throw new NoActiveProductsException("Ошибка! Нет активных продуктов для вычисления суммарной стоимости.");
        }
        return totalPrice;
    }

    @Override
    public double getActiveProductAveragePrice() {
        OptionalDouble averagePrice = repository.findAll()
                .stream()
                .filter(p -> p.isActive())
                .mapToDouble(p -> p.getPrice())
                .average();
        //.orElse(0);
        if (averagePrice.isPresent()) {
            return averagePrice.getAsDouble();
        } else {
            throw new NoActiveProductsException("Ошибка! Нет активных продуктов для вычисления средней цены.");
        }
    }
}
