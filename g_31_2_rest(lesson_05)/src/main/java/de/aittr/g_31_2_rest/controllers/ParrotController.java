package de.aittr.g_31_2_rest.controllers;

import de.aittr.g_31_2_rest.domain.Parrot;
import de.aittr.g_31_2_rest.servises.ParrotService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parrots") // эндпоинт
public class ParrotController {
    private ParrotService service;

    public ParrotController(ParrotService service) {
        this.service = service;
    }

    // get запрос
    @GetMapping // отвечает на get - запросы
    public List<Parrot> getAll(){
        return service.getAll();
    }


     @GetMapping("/{id}") // get запрос
    public Parrot getById(@PathVariable int id){
        return service.getById(id);
     }

     // post запрос
     @PostMapping
     public Parrot save(@RequestBody Parrot parrot){ // пришел json, Spring его преобразовал в объект
        return service.save(parrot);

     }
     @DeleteMapping("/{id}") // delete  запрос

     public void deleteById(@PathVariable int id){
        service.deleteById(id);
     }
}
