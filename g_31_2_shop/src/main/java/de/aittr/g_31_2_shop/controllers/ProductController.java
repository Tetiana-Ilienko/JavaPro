package de.aittr.g_31_2_shop.controllers;

import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.exeption_handling.Response;
import de.aittr.g_31_2_shop.exeption_handling.exceptions.FirstTestException;
import de.aittr.g_31_2_shop.services.interfaces.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    // контроллер будет работать только с объектами Dto
    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ProductDto save(@Valid @RequestBody ProductDto product) {
        return service.save(product);
    }

    @GetMapping
    public List<ProductDto> getAllActiveProduct() {
        return service.getAllActiveProducts();
    }

    /** Обработка ошибок первый способ - выкидываем ошибку в контроллере*/
//    @GetMapping("/{id}")
//    public ProductDto getActiveProductById(@PathVariable int id) {
//        ProductDto dto =  service.getActiveProductById(id);
//        if (dto==null){
//            throw new FirstTestException("Продукт с указанным идентификатором отсутствует в базе данных.");
//        }
//        return dto;
//    }


    @GetMapping("/{id}")
    public ProductDto getActiveProductById(@PathVariable int id) {
        return service.getActiveProductById(id);
    }

    @PutMapping
    public void update(@RequestBody ProductDto product) {
        service.update(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        service.deleteById(id);
    }

    @DeleteMapping("/name/{name}")
    public void deleteByName(@PathVariable String name){
        service.deleteByName(name);
    }

    @PutMapping("/{id}")
    public void restoreById(@PathVariable int id) {
        service.restoreById(id);
    }



    /** первый способ создания метода-обработчика в контроллере, где мы ожидаем ошибки
     *  Минус - если в разных контроллерах требуется обрабатывать ошибки одинаково,
     *  то нам придётся написать один и тот же обработчик в разных контроллерах.
     *  Плюс - если в разных контроллерах требуется обрабатывать ошибки по-разному,
     *  то такой подход позволяет нам это сделать.*/
    @ExceptionHandler(FirstTestException.class)// для Spring
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)// статус ошибки, отправленный клиенту
    public Response handleException(FirstTestException e){
        return new Response(e.getMessage());
    }
    // метод вызывает сам Spring при возникновении ошибки


}
