package de.aittr.g_31_2_rest.controllers;

import de.aittr.g_31_2_rest.domain.Cat;
import de.aittr.g_31_2_rest.servises.CatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {

    // http://google.com
    // http://12.34.56.78:8080/cats - получить список всех котов
    // http://12.34.56.78:8080/dogs - получить список всех собак
    // http://12.34.56.78:8080/cats/5 - получить кота с ИД 5
    // http://12.34.56.78:8080/cats/delete/3 - удалить кота с ИД 3
    // http://12.34.56.78:8080/cows - несуществующий эндпоинт - клиент получит 404


    private CatService service;

    public CatController(CatService service) {
        this.service = service;
    }

    //    @GetMapping("/test")
//    - метод принимает GET запросы на эндпоинт http://12.34.56.78:8080/cats/test
    @GetMapping
    //    - метод принимает GET запросы на эндпоинт http://12.34.56.78:8080/cats
    public List<Cat> getAll() {
        return service.getAll();
    }

//    // 1 способ Кота по id
    //  http://12.34.56.78:8080/cats/5
    @GetMapping("/{id}")
    public Cat getById(@PathVariable int id){
        return service.getById(id);
    }


    // 2 способ получения кота по ИД - передача ИД как параметра запроса
    // http://12.34.56.78:8080/cats/get?id=3&color=Gray
//    @GetMapping("/get")
//    public Cat getById(@RequestParam int id){
//        return service.getById(id);
//    }
}
